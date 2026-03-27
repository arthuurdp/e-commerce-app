package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.OrderApiService
import com.ecommerce.app.data.model.order.*
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class OrderRepository @Inject constructor(private val api: OrderApiService) : BaseRepository() {

    suspend fun getOrders(page: Int = 0, size: Int = 10): NetworkResult<PageResponse<OrderResponse>> =
        safeApiCall { api.getOrders(page, size) }

    suspend fun getOrderById(id: Long): NetworkResult<OrderDetailsResponse> =
        safeApiCall { api.getOrderById(id) }

    suspend fun checkout(request: CheckoutRequest): NetworkResult<CheckoutResponse> =
        safeApiCall { api.checkout(request) }
}