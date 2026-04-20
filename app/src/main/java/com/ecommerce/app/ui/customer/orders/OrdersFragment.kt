package com.ecommerce.app.ui.customer.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecommerce.app.R
import com.ecommerce.app.databinding.FragmentOrderListBinding
import com.ecommerce.app.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrdersViewModel by viewModels()
    private lateinit var ordersAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ordersAdapter = OrderAdapter { order ->
            findNavController().navigate(
                R.id.action_ordersFragment_to_orderDetailFragment,
                bundleOf("orderId" to order.id)
            )
        }

        binding.rvOrders.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ordersAdapter
            addDivider()
        }

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadOrders() }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        observeOrders()
        viewModel.loadOrders()
    }

    private fun observeOrders() {
        viewModel.ordersState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    binding.progressBar.show()
                    binding.tvEmptyOrders.hide()
                }
                is NetworkResult.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.progressBar.hide()
                    val orders = result.data.content
                    ordersAdapter.submitList(orders)
                    if (orders.isEmpty()) {
                        binding.tvEmptyOrders.show()
                        binding.rvOrders.hide()
                    } else {
                        binding.tvEmptyOrders.hide()
                        binding.rvOrders.show()
                    }
                }
                is NetworkResult.Error -> {
                    binding.swipeRefresh.isRefreshing = false
                    binding.progressBar.hide()
                    binding.tvEmptyOrders.show()
                    binding.rvOrders.hide()
                    showToast(result.message)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}