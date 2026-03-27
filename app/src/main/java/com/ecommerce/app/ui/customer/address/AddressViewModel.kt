package com.ecommerce.app.ui.customer.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.address.AddressResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.AddressRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel() {

    private val _addressesState = MutableLiveData<NetworkResult<PageResponse<AddressResponse>>>()
    val addressesState: LiveData<NetworkResult<PageResponse<AddressResponse>>> = _addressesState

    private val _deleteState = MutableLiveData<NetworkResult<Unit>>()
    val deleteState: LiveData<NetworkResult<Unit>> = _deleteState

    fun loadAddresses() {
        viewModelScope.launch {
            _addressesState.value = NetworkResult.Loading
            _addressesState.value = addressRepository.getAddresses()
        }
    }

    fun deleteAddress(id: Long) {
        viewModelScope.launch {
            _deleteState.value = NetworkResult.Loading
            _deleteState.value = addressRepository.deleteAddress(id)
        }
    }
}
