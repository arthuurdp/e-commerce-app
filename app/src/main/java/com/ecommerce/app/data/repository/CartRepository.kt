package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.CartApiService
import com.ecommerce.app.data.model.cart.CartItemResponse
import com.ecommerce.app.data.model.cart.CartResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class CartRepository @Inject constructor(private val api: CartApiService) : BaseRepository() {

    suspend fun getCart(): NetworkResult<CartResponse> =
        safeApiCall { api.getCart() }

    suspend fun addToCart(productId: Long): NetworkResult<CartItemResponse> =
        safeApiCall { api.addToCart(productId) }

    suspend fun removeFromCart(productId: Long): NetworkResult<CartItemResponse?> =
        safeApiCall { api.removeFromCart(productId) }

    suspend fun clearCart(): NetworkResult<Unit> =
        safeApiCall { api.clearCart() }
}