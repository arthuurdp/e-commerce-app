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
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.model.OrderResponse
import com.ecommerce.app.data.repository.OrderRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminOrdersViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    private val _ordersState = MutableLiveData<NetworkResult<PageResponse<OrderResponse>>>()
    val ordersState: LiveData<NetworkResult<PageResponse<OrderResponse>>> = _ordersState

    fun loadOrders() {
        viewModelScope.launch {
            _ordersState.value = NetworkResult.Loading
            _ordersState.value = orderRepository.getOrders(size = 50)
        }
    }
}

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
        val adapter = OrderAdapter { /* open detail */ }
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
