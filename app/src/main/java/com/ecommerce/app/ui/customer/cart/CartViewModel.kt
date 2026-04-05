package com.ecommerce.app.ui.customer.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.cart.CartResponse
import com.ecommerce.app.data.repository.AddressRepository
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _cartState = MutableLiveData<NetworkResult<CartResponse>>()
    val cartState: LiveData<NetworkResult<CartResponse>> = _cartState

    private val _addressStatus = MutableLiveData<NetworkResult<Boolean>>()
    val addressStatus: LiveData<NetworkResult<Boolean>> = _addressStatus

    fun loadCart() {
        viewModelScope.launch {
            _cartState.value = NetworkResult.Loading
            _cartState.value = cartRepository.getCart()
        }
    }

    fun checkAddressesBeforeCheckout() {
        viewModelScope.launch {
            _addressStatus.value = NetworkResult.Loading
            val result = addressRepository.getAddresses()
            if (result is NetworkResult.Success) {
                val hasAddresses = result.data.content.isNotEmpty()
                _addressStatus.value = NetworkResult.Success(hasAddresses)
            } else if (result is NetworkResult.Error) {
                _addressStatus.value = NetworkResult.Error(result.message)
            }
        }
    }

    fun addToCart(productId: Long) {
        viewModelScope.launch {
            val result = cartRepository.addToCart(productId)
            if (result is NetworkResult.Error) {
                _cartState.value = NetworkResult.Error(result.message)
            } else {
                loadCart()
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