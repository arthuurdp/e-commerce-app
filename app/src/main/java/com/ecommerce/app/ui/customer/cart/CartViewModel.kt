package com.ecommerce.app.ui.customer.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.CartResponse
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartState = MutableLiveData<NetworkResult<CartResponse>>()
    val cartState: LiveData<NetworkResult<CartResponse>> = _cartState

    fun loadCart() {
        viewModelScope.launch {
            _cartState.value = NetworkResult.Loading
            _cartState.value = cartRepository.getCart()
        }
    }

    fun addToCart(productId: Long) {
        viewModelScope.launch {
            val result = cartRepository.addToCart(productId)
            if (result is NetworkResult.Error) {
                // Re-expose error but keep existing cart data visible
                _cartState.value = NetworkResult.Error(result.message)
            } else {
                loadCart() // Refresh full cart after change
            }
        }
    }

    fun removeFromCart(productId: Long) {
        viewModelScope.launch {
            val result = cartRepository.removeFromCart(productId)
            if (result is NetworkResult.Error) {
                _cartState.value = NetworkResult.Error(result.message)
            } else {
                loadCart()
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            val result = cartRepository.clearCart()
            if (result is NetworkResult.Error) {
                _cartState.value = NetworkResult.Error(result.message)
            } else {
                loadCart()
            }
        }
    }
}
