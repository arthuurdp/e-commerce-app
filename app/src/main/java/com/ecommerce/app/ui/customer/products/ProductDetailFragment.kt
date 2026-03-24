package com.ecommerce.app.ui.customer.products

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecommerce.app.R
import androidx.viewpager2.widget.ViewPager2
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.data.model.ProductDetailsResponse
import com.ecommerce.app.data.model.ProductImageResponse
import com.ecommerce.app.databinding.FragmentProductDetailBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentProductDetailBinding.inflate(inflater, container, false)
        .also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getLong("productId") ?: return

        binding.ivBack.setOnClickListener { findNavController().popBackStack() }
        binding.ivCart.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailFragment_to_cartFragment)
        }

        viewModel.loadProduct(productId)
        observeProduct()
        observeAddToCart()
    }

    private fun observeProduct() {
        viewModel.product.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    bindProduct(result.data)
                }

                is NetworkResult.Error -> {
                    binding.progressBar.hide()
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
            when (result) {
                is NetworkResult.Loading -> {
                    binding.btnAddToCart.isEnabled = false
                }

                is NetworkResult.Success -> {
                    binding.btnAddToCart.isEnabled = true

                    Toast.makeText(
                        requireContext(),
                        "Produto adicionado ao carrinho",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Error -> {
                    binding.btnAddToCart.isEnabled = true

                    showVerifyEmailDialog()
                }
            }
        }
    }

    private fun showVerifyEmailDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_verify_email)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.85).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.findViewById<TextView>(R.id.btnCancel).setOnClickListener {
            dialog.dismiss()
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
