package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.AddressApiService
import com.ecommerce.app.data.model.address.*
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class AddressRepository @Inject constructor(private val api: AddressApiService) : BaseRepository() {
    suspend fun getAddresses(): NetworkResult<PageResponse<AddressResponse>> =
        safeApiCall { api.getAddresses() }

    suspend fun getAddressById(id: Long): NetworkResult<AddressResponse> =
        safeApiCall { api.getAddressById(id) }

    suspend fun createAddress(request: CreateAddressRequest): NetworkResult<AddressResponse> =
        safeApiCall { api.createAddress(request) }

    suspend fun updateAddress(id: Long, request: UpdateAddressRequest): NetworkResult<AddressResponse> =
        safeApiCall { api.updateAddress(id, request) }

    suspend fun deleteAddress(id: Long): NetworkResult<Unit> =
        safeApiCall { api.deleteAddress(id) }

    suspend fun lookupCep(cep: String): NetworkResult<CepLookupResponse> =
        safeApiCall { api.lookupCep(cep) }
}