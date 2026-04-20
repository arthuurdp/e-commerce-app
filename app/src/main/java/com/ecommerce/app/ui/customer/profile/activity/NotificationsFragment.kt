package com.ecommerce.app.ui.customer.profile.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerce.app.R
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.databinding.FragmentNotificationsBinding
import com.ecommerce.app.util.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotificationsViewModel by viewModels()

    private val notificationsAdapter = NotificationsAdapter { notification ->
        if (notification.read == false) {
            viewModel.markAsRead(notification.id)
        }
    }
    private val reviewsAdapter by lazy {
        ReviewsAdapter(
            onEditClick = { review -> showEditReviewDialog(review) },
            onDeleteClick = { review -> viewModel.deleteReview(review.id) },
            onSeeProductClick = { productId ->
                findNavController().navigate(
                    R.id.action_notificationsFragment_to_productDetailFragment,
                    Bundle().apply { putLong("productId", productId) }
                )
            }
        )
    }
    private val favoritesAdapter by lazy {
        FavoriteAdapter(
            onItemClick = { product ->
                findNavController().navigate(
                    R.id.action_notificationsFragment_to_productDetailFragment,
                    Bundle().apply { putLong("productId", product.id) }
                )
            },
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }

        binding.btnClearAll.setOnClickListener {
            viewModel.clearRecentActivity()
        }

        setupRecyclerView()
        setupFilters()
        setupSwipeRefresh()
        observeData()
        observeRemoval()
        observeReviewActions()
        observeClearActivity()
    }

    private fun showEditReviewDialog(review: ReviewResponse) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_add_review)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            (resources.displayMetrics.widthPixels * 0.90).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val title = dialog.findViewById<TextView>(R.id.tv_review_title)
        val ratingBar = dialog.findViewById<RatingBar>(R.id.rating_bar_input)
        val commentInput = dialog.findViewById<TextInputEditText>(R.id.et_comment)
        val btnSubmit = dialog.findViewById<MaterialButton>(R.id.btn_submit_review)
        val btnCancel = dialog.findViewById<TextView>(R.id.btn_cancel_review)

        title.text = "Editar avaliação"
        ratingBar.rating = review.rating.toFloat()
        commentInput.setText(review.comment?.content.orEmpty())
        btnSubmit.text = "Salvar alterações"

        btnCancel.setOnClickListener { dialog.dismiss() }
        btnSubmit.setOnClickListener {
            val rating = ratingBar.rating.toInt()
            val updatedComment = commentInput.text?.toString()?.trim().orEmpty()
            val currentComment = review.comment?.content?.trim().orEmpty()

            if (rating == 0) {
                showToast("Por favor, selecione uma nota")
                return@setOnClickListener
            }

            if (rating == review.rating && updatedComment == currentComment) {
                showToast("Nenhuma alteração para salvar")
                return@setOnClickListener
            }

            viewModel.editReview(review, rating, updatedComment)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun observeClearActivity() {
        viewModel.clearActivityState.observe(viewLifecycleOwner) { result ->
            if (result == null) return@observe
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast("Notificações limpas!")
                    viewModel.resetClearActivityState()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                    viewModel.resetClearActivityState()
                }
            }
        }
    }

    private fun observeReviewActions() {
        viewModel.editReviewResult.observe(viewLifecycleOwner) { result ->
            if (result == null) return@observe
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast("Avaliação atualizada!")
                    viewModel.resetEditReview()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                    viewModel.resetEditReview()
                }
            }
        }

        viewModel.deleteReviewResult.observe(viewLifecycleOwner) { result ->
            if (result == null) return@observe
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast("Avaliação excluída.")
                    viewModel.resetDeleteReview()
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                    viewModel.resetDeleteReview()
                }
            }
        }
    }

    private fun observeRemoval() {
        viewModel.removeFavoriteResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    showToast("Removido dos favoritos")
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvActivities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = notificationsAdapter
            addDivider()
        }
    }

    private fun setupFilters() {
        binding.chipGroupFilters.setOnCheckedStateChangeListener { _, checkedIds ->
            val checkedId = checkedIds.firstOrNull() ?: R.id.chip_all
            updateAdapterAndLoadData(checkedId)
        }
    }

    private fun updateAdapterAndLoadData(checkedId: Int) {
        binding.rvActivities.adapter = null
        binding.tvEmpty.hide()
        when (checkedId) {
            R.id.chip_all -> {
                binding.btnClearAll.show()
                binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
                binding.rvActivities.adapter = notificationsAdapter
                viewModel.loadRecentActivity()
            }
            R.id.chip_favorites -> {
                binding.btnClearAll.hide()
                binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
                binding.rvActivities.adapter = favoritesAdapter
                viewModel.loadMyFavorites()
            }
            R.id.chip_reviews -> {
                binding.btnClearAll.hide()
                binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
                binding.rvActivities.adapter = reviewsAdapter
                viewModel.loadMyReviews()
            }
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            val checkedId = binding.chipGroupFilters.checkedChipId
            when (checkedId) {
                R.id.chip_all -> viewModel.loadRecentActivity()
                R.id.chip_favorites -> viewModel.loadMyFavorites()
                R.id.chip_reviews -> viewModel.loadMyReviews()
            }
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun observeData() {
        viewModel.recentActivity.observe(viewLifecycleOwner) { result ->
            if (binding.chipGroupFilters.checkedChipId == R.id.chip_all) {
                handleResult(result) { notificationsAdapter.submitList(it) }
            }
        }
        viewModel.myReviews.observe(viewLifecycleOwner) { result ->
            if (binding.chipGroupFilters.checkedChipId == R.id.chip_reviews) {
                handleResult(result) { reviewsAdapter.submitList(it) }
            }
        }
        viewModel.myFavorites.observe(viewLifecycleOwner) { result ->
            if (binding.chipGroupFilters.checkedChipId == R.id.chip_favorites) {
                handleResult(result) { favoritesAdapter.submitList(it.toList()) }
            }
        }
    }

    private fun <T> handleResult(result: NetworkResult<T>, onSuccess: (T) -> Unit) {
        when (result) {
            is NetworkResult.Loading -> {
                binding.progressBar.show()
                binding.tvEmpty.hide()
            }
            is NetworkResult.Success -> {
                binding.progressBar.hide()
                val data = result.data
                if (data == null || (data is Collection<*> && data.isEmpty())) {
                    binding.tvEmpty.show()
                    binding.rvActivities.hide()
                } else {
                    binding.tvEmpty.hide()
                    binding.rvActivities.show()
                    onSuccess(data)
                }
            }
            is NetworkResult.Error -> {
                binding.progressBar.hide()
                showToast(result.message)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
