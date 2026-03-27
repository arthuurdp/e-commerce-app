package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.order.*
import com.ecommerce.app.data.model.shipping.*
import com.ecommerce.app.data.model.util.PageResponse
import retrofit2.Response
import retrofit2.http.*

interface OrderApiService {

    @POST("orders/checkout")
    suspend fun checkout(@Body request: CheckoutRequest): Response<CheckoutResponse>

    @GET("orders")
    suspend fun getOrders(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<PageResponse<OrderResponse>>

    @GET("orders/{id}")
    suspend fun getOrderById(@Path("id") id: Long): Response<OrderDetailsResponse>

    @GET("orders/{orderId}/shipping")
    suspend fun getShipping(@Path("orderId") orderId: Long): Response<ShippingResponse>

    @GET("freights")
    suspend fun calculateFreight(@Query("postalCode") postalCode: String): Response<List<FreightResponse>>
}