package com.ecommerce.app.ui.customer.profile.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentNotificationsBinding
import com.ecommerce.app.ui.customer.products.CommentAdapter
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotificationsViewModel by viewModels()
    
    private val notificationsAdapter = NotificationsAdapter()
    private val reviewsAdapter by lazy { ReviewsAdapter { /* Handle click */ } }
    private val commentsAdapter by lazy { CommentAdapter(null) { /* Handle delete */ } }
    private val favoritesAdapter by lazy {
        FavoriteAdapter(
            onItemClick = { product ->
                val bundle = Bundle().apply {
                    putLong("productId", product.id)
                }
                findNavController().navigate(
                    R.id.action_notificationsFragment_to_productDetailFragment,
                    bundle
                )
            },
            onRemoveClick = { product ->
                viewModel.removeFavorite(product.id)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        setupRecyclerView()
        setupFilters()
        setupSwipeRefresh()
        observeData()
        observeRemoval()
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
                binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
                binding.rvActivities.adapter = notificationsAdapter
                viewModel.loadRecentActivity()
            }
            R.id.chip_favorites -> {
                binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
                binding.rvActivities.adapter = favoritesAdapter
                viewModel.loadMyFavorites()
            }
            R.id.chip_reviews -> {
                binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
                binding.rvActivities.adapter = reviewsAdapter
                viewModel.loadMyReviews()
            }
            R.id.chip_comments -> {
                binding.rvActivities.layoutManager = LinearLayoutManager(requireContext())
                binding.rvActivities.adapter = commentsAdapter
                viewModel.loadMyComments()
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
                R.id.chip_comments -> viewModel.loadMyComments()
            }
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun observeData() {
        viewModel.recentActivity.observe(viewLifecycleOwner) { result ->
            if (binding.chipGroupFilters.checkedChipId == R.id.chip_all) {
                handleResult(result) { data ->
                    notificationsAdapter.submitList(data)
                }
            }
        }

        viewModel.myReviews.observe(viewLifecycleOwner) { result ->
            if (binding.chipGroupFilters.checkedChipId == R.id.chip_reviews) {
                handleResult(result) { data ->
                    reviewsAdapter.submitList(data)
                }
            }
        }

        viewModel.myComments.observe(viewLifecycleOwner) { result ->
            if (binding.chipGroupFilters.checkedChipId == R.id.chip_comments) {
                handleResult(result) { data ->
                    commentsAdapter.submitList(data)
                }
            }
        }

        viewModel.myFavorites.observe(viewLifecycleOwner) { result ->
            if (binding.chipGroupFilters.checkedChipId == R.id.chip_favorites) {
                handleResult(result) { data ->
                    favoritesAdapter.submitList(data.toList())
                }
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
