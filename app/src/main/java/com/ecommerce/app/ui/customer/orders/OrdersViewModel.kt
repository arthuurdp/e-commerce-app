package com.ecommerce.app.ui.customer.orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ecommerce.app.data.model.OrderResponse
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.model.order.OrderResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.OrderRepository
import com.ecommerce.app.databinding.ItemOrderBinding
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.toCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ─── ViewModel ────────────────────────────────────────────────────────────────

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _ordersState = MutableLiveData<NetworkResult<PageResponse<OrderResponse>>>()
    val ordersState: LiveData<NetworkResult<PageResponse<OrderResponse>>> = _ordersState

    fun loadOrders() {
        viewModelScope.launch {
            _ordersState.value = NetworkResult.Loading
            _ordersState.value = orderRepository.getOrders()
        }
    }
}

