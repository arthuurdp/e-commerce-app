package com.ecommerce.app.ui.admin.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentAdminProductsBinding
import com.ecommerce.app.ui.customer.products.ProductAdapter
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminProductsFragment : Fragment() {
    private var _binding: FragmentAdminProductsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminProductsViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter { product ->
            findNavController().navigate(
                R.id.action_adminProductsFragment_to_adminEditProductFragment,
                bundleOf("productId" to product.id)
            )
        }
        binding.rvProducts.adapter = adapter

        binding.fabAddProduct.setOnClickListener {
            findNavController().navigate(
                R.id.action_adminProductsFragment_to_adminEditProductFragment,
                bundleOf("productId" to -1L)
            )
        }

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadProducts() }

        observeProducts()
        viewModel.loadProducts()
    }

    private fun observeProducts() {
        viewModel.productsState.observe(viewLifecycleOwner) { result ->
            binding.swipeRefresh.isRefreshing = false
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(result.data.content)
                }
                is NetworkResult.Error -> {
                    binding.progressBar.hide()
                    showToast(result.message)
                }
            }
        }

        viewModel.deleteState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    showToast("Product deleted")
                    viewModel.loadProducts()
                }
                is NetworkResult.Error -> showToast(result.message)
                else -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
