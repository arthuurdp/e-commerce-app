package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.address.*
import com.ecommerce.app.data.model.util.PageResponse
import retrofit2.Response
import retrofit2.http.*

interface AddressApiService {

    @GET("addresses")
    suspend fun getAddresses(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<PageResponse<AddressResponse>>

    @POST("addresses")
    suspend fun createAddress(@Body request: CreateAddressRequest): Response<AddressResponse>

    @PATCH("addresses/{id}")
    suspend fun updateAddress(
        @Path("id") id: Long,
        @Body request: UpdateAddressRequest
    ): Response<AddressResponse>

    @DELETE("addresses/{id}")
    suspend fun deleteAddress(@Path("id") id: Long): Response<Unit>

    @GET("cities/lookup")
    suspend fun lookupCep(@Query("cep") cep: String): Response<CepLookupResponse>
}