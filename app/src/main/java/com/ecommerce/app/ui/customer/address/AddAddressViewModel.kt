package com.ecommerce.app.ui.customer.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.address.CepLookupResponse
import com.ecommerce.app.data.model.address.CreateAddressRequest
import com.ecommerce.app.data.repository.AddressRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAddressViewModel @Inject constructor(
    private val addressRepository: AddressRepository
) : ViewModel() {
    private val _cepState = MutableLiveData<NetworkResult<CepLookupResponse>>()
    val cepState: LiveData<NetworkResult<CepLookupResponse>> = _cepState

    private val _saveState = MutableLiveData<NetworkResult<*>>()
    val saveState: LiveData<NetworkResult<*>> = _saveState

    fun lookupCep(cep: String) {
        viewModelScope.launch {
            _cepState.value = NetworkResult.Loading
            _cepState.value = addressRepository.lookupCep(cep)
        }
    }

    fun saveAddress(request: CreateAddressRequest) {
        viewModelScope.launch {
            _saveState.value = NetworkResult.Loading
            _saveState.value = addressRepository.createAddress(request)
        }
    }
}