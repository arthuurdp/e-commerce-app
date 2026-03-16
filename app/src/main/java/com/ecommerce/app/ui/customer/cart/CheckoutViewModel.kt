package com.ecommerce.app.ui.customer.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.CheckoutRequest
import com.ecommerce.app.data.model.CheckoutResponse
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.model.AddressResponse
import com.ecommerce.app.data.repository.AddressRepository
import com.ecommerce.app.data.repository.OrderRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _addressesState = MutableLiveData<NetworkResult<PageResponse<AddressResponse>>>()
    val addressesState: LiveData<NetworkResult<PageResponse<AddressResponse>>> = _addressesState

    private val _checkoutState = MutableLiveData<NetworkResult<CheckoutResponse>>()
    val checkoutState: LiveData<NetworkResult<CheckoutResponse>> = _checkoutState

    fun loadAddresses() {
        viewModelScope.launch {
            _addressesState.value = NetworkResult.Loading
            _addressesState.value = addressRepository.getAddresses()
        }
    }

    fun checkout(request: CheckoutRequest) {
        viewModelScope.launch {
            _checkoutState.value = NetworkResult.Loading
            _checkoutState.value = orderRepository.checkout(request)
        }
    }
}
