package com.ecommerce.app.ui.customer.products

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecommerce.app.R
import androidx.viewpager2.widget.ViewPager2
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.data.model.product.ProductDetailsResponse
import com.ecommerce.app.data.model.product.ProductImageResponse
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.FragmentProductDetailBinding
import com.ecommerce.app.ui.customer.profile.security.SecurityViewModel
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()
    private val securityViewModel: SecurityViewModel by viewModels()
    private var userEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentProductDetailBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getLong("productId") ?: return

        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        viewModel.loadProduct(productId)
        observeProduct()
        observeAddToCart()
        observeReviews()
        observeFavorite()
        observeAddReview()

        binding.btnFavorite.setOnClickListener {
            viewModel.toggleFavorite(productId)
        }

        binding.btnAddReview.setOnClickListener {
            showAddReviewDialog(productId)
        }

        viewModel.userEmail.observe(viewLifecycleOwner) { email ->
            userEmail = email
        }
    }

    private fun observeProduct() {
        viewModel.product.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.layoutLoading.loadingOverlay.show()
                is NetworkResult.Success -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    bindProduct(result.data)
                }

                is NetworkResult.Error -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindProduct(p: ProductDetailsResponse) {
        binding.tvName.text = p.name
        binding.tvDescription.text = p.description
        binding.tvPrice.text = "R$ %.2f".format(p.price)
        binding.tvCategory.text = p.categories.joinToString(" · ") { it.name }

        setupImageCarousel(p.imgs)

        binding.btnAddToCart.setOnClickListener {
            viewModel.addToCart(p.id)
        }
    }

    private fun observeAddToCart() {
        viewModel.addToCartState.observe(viewLifecycleOwner) { result ->
            if (result == null) return@observe

            when (result) {
                is NetworkResult.Loading -> {
                    binding.btnAddToCart.isEnabled = false
                    binding.layoutLoading.loadingOverlay.show()
                }

                is NetworkResult.Success -> {
                    binding.btnAddToCart.isEnabled = true
                    binding.layoutLoading.loadingOverlay.hide()
                    showProductAddedToCartDialog()
                }

                is NetworkResult.Error -> {
                    binding.btnAddToCart.isEnabled = true
                    binding.layoutLoading.loadingOverlay.hide()
                    showVerifyEmailDialog()
                }
            }
        }
    }

    private fun observeAddReview() {
        viewModel.addReviewState.observe(viewLifecycleOwner) { result ->
            if (result == null) return@observe

            when (result) {
                is NetworkResult.Loading -> binding.layoutLoading.loadingOverlay.show()
                is NetworkResult.Success -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    Toast.makeText(requireContext(), "Avaliação enviada com sucesso!", Toast.LENGTH_SHORT).show()
                    viewModel.resetAddReviewState()
                }
                is NetworkResult.Error -> {
                    binding.layoutLoading.loadingOverlay.hide()
                    Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    viewModel.resetAddReviewState()
                }
            }
        }
    }

    private fun showAddReviewDialog(productId: Long) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_review)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.90).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val ratingBar = dialog.findViewById<RatingBar>(R.id.rating_bar_input)
        val etComment = dialog.findViewById<EditText>(R.id.et_comment)
        val btnSubmit = dialog.findViewById<MaterialButton>(R.id.btn_submit_review)
        val btnCancel = dialog.findViewById<TextView>(R.id.btn_cancel_review)

        btnCancel.setOnClickListener { dialog.dismiss() }

        btnSubmit.setOnClickListener {
            val rating = ratingBar.rating.toInt()
            if (rating == 0) {
                Toast.makeText(requireContext(), "Por favor, selecione uma nota", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val comment = etComment.text.toString()
            viewModel.addReview(productId, rating, comment.ifBlank { null })
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showVerifyEmailDialog() {
        viewModel.resetAddToCartState()

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_verify_email)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.80).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.findViewById<TextView>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<TextView>(R.id.btnConfirm).setOnClickListener {
            securityViewModel.sendEmailVerification()
            dialog.dismiss()
            findNavController().navigate(
                R.id.action_productDetailFragment_to_enterCodeFragment,
                Bundle().apply {
                    putString("mode", "verify_email")
                    putString("email", userEmail ?: "")
                }
            )
        }

        dialog.show()
    }

    private fun observeFavorite() {
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            binding.btnFavorite.setImageResource(
                if (isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
            )
        }

        viewModel.favoriteState.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Error) {
                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                viewModel.resetFavoriteState()
            }
        }
    }

    private fun observeReviews() {
        viewModel.averageRating.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                binding.tvRatingAvg.text = "%.1f".format(result.data)
                binding.ratingBarAvg.rating = result.data.toFloat()
            }
        }

        viewModel.reviews.observe(viewLifecycleOwner) { result ->
            if (result is NetworkResult.Success) {
                binding.tvReviewsCount.text = "(%d avaliações)".format(result.data.size)
            }
        }
    }

    private fun showProductAddedToCartDialog() {
        viewModel.resetAddToCartState()

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_more_products)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.80).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.findViewById<TextView>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(R.id.action_productDetailFragment_to_cartFragment)
        }

        dialog.findViewById<TextView>(R.id.btnConfirm).setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setupImageCarousel(imgs: List<ProductImageResponse>) {
        binding.vpProductImages.adapter = ProductItemAdapter(imgs)
        setupDots(imgs.size)

        binding.vpProductImages.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) = updateDots(position, imgs.size)
            }
        )
    }

    private fun setupDots(count: Int) {
        binding.llDots.removeAllViews()
        repeat(count) {
            val dot = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(8.dp, 8.dp)
                    .also { p -> p.marginEnd = 6.dp }
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_dot_inactive)
            }
            binding.llDots.addView(dot)
        }
        updateDots(0, count)
    }

    private fun updateDots(selected: Int, count: Int) {
        for (i in 0 until count) {
            binding.llDots.getChildAt(i)?.background = ContextCompat.getDrawable(
                requireContext(),
                if (i == selected) R.drawable.bg_dot_active else R.drawable.bg_dot_inactive
            )
        }
    }

    private val Int.dp get() = (this * resources.displayMetrics.density).toInt()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
