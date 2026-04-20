package com.ecommerce.app.ui.customer.cart.checkout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.address.AddressResponse
import com.ecommerce.app.data.model.cart.CartResponse
import com.ecommerce.app.data.model.order.CheckoutRequest
import com.ecommerce.app.data.model.order.CheckoutResponse
import com.ecommerce.app.data.model.shipping.FreightResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.AddressRepository
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.data.repository.OrderRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val addressRepository: AddressRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _addressesState = MutableLiveData<NetworkResult<PageResponse<AddressResponse>>>()
    val addressesState: LiveData<NetworkResult<PageResponse<AddressResponse>>> = _addressesState

    private val _cartState = MutableLiveData<NetworkResult<CartResponse>>()
    val cartState: LiveData<NetworkResult<CartResponse>> = _cartState

    private val _freightState = MutableLiveData<NetworkResult<List<FreightResponse>>>()
    val freightState: LiveData<NetworkResult<List<FreightResponse>>> = _freightState

    private val _checkoutState = MutableLiveData<NetworkResult<CheckoutResponse>?>()
    val checkoutState: LiveData<NetworkResult<CheckoutResponse>?> = _checkoutState

    fun loadInitialData() {
        viewModelScope.launch {
            val cartDeferred = async { cartRepository.getCart() }
            val addressDeferred = async { addressRepository.getAddresses() }

            _cartState.value = NetworkResult.Loading
            _addressesState.value = NetworkResult.Loading

            _cartState.value = cartDeferred.await()

            val addressResult = addressDeferred.await()
            _addressesState.value = addressResult

            if (addressResult is NetworkResult.Success) {
                val postalCode = addressResult.data.content
                    .firstOrNull()?.postalCode?.replace("-", "")?.trim()
                if (!postalCode.isNullOrBlank() && postalCode.length == 8) {
                    loadFreight(postalCode)
                } else {
                    _freightState.value = NetworkResult.Success(emptyList())
                }
            } else {
                _freightState.value = NetworkResult.Success(emptyList())
            }
        }
    }

    fun loadFreight(postalCode: String) {
        viewModelScope.launch {
            _freightState.value = NetworkResult.Loading
            _freightState.value = orderRepository.calculateFreight(postalCode)
        }
    }

    fun checkout(request: CheckoutRequest) {
        viewModelScope.launch {
            _checkoutState.value = NetworkResult.Loading
            _checkoutState.value = orderRepository.checkout(request)
        }
    }

    fun onCheckoutHandled() {
        _checkoutState.value = null
    }
}