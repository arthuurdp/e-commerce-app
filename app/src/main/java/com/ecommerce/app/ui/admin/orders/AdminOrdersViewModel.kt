package com.ecommerce.app.ui.admin.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.order.OrderResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.OrderRepository
import com.ecommerce.app.util.NetworkResult
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