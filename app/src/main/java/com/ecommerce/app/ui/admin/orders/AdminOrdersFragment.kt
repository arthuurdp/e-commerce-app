package com.ecommerce.app.ui.admin.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ecommerce.app.databinding.FragmentAdminOrdersBinding
import com.ecommerce.app.ui.customer.orders.OrderAdapter
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.ecommerce.app.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminOrdersFragment : Fragment() {
    private var _binding: FragmentAdminOrdersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AdminOrdersViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdminOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = OrderAdapter { }
        binding.rvOrders.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener { viewModel.loadOrders() }
        viewModel.ordersState.observe(viewLifecycleOwner) { result ->
            binding.swipeRefresh.isRefreshing = false
            when (result) {
                is NetworkResult.Loading -> binding.progressBar.show()
                is NetworkResult.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(result.data.content)
                    binding.tvEmpty.visibility =
                        if (result.data.content.isEmpty()) View.VISIBLE else View.GONE
                }
                is NetworkResult.Error -> { binding.progressBar.hide(); showToast(result.message) }
            }
        }
        viewModel.loadOrders()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
