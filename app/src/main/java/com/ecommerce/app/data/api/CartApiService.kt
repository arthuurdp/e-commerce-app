package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.cart.*
import retrofit2.Response
import retrofit2.http.*

interface CartApiService {

    @GET("cart")
    suspend fun getCart(): Response<CartResponse>

    @PATCH("cart/{productId}/increment")
    suspend fun addToCart(@Path("productId") productId: Long): Response<CartItemResponse>

    @PATCH("cart/{productId}/decrement")
    suspend fun removeFromCart(@Path("productId") productId: Long): Response<CartItemResponse?>

    @DELETE("cart")
    suspend fun clearCart(): Response<Unit>
}