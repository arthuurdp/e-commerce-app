package com.ecommerce.app.ui.customer.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.order.OrderDetailsResponse
import com.ecommerce.app.data.repository.OrderRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _orderState = MutableLiveData<NetworkResult<OrderDetailsResponse>>()
    val orderState: LiveData<NetworkResult<OrderDetailsResponse>> = _orderState

    fun loadOrder(id: Long) {
        viewModelScope.launch {
            _orderState.value = NetworkResult.Loading
            _orderState.value = orderRepository.getOrderById(id)
        }
    }
}
