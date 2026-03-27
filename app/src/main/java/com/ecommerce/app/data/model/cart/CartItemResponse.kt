package com.ecommerce.app.data.model.cart

data class CartItemResponse(
    val productId: Long,
    val imageUrl: String?,
    val name: String,
    val price: Double,
    val quantity: Int,
    val subtotal: Double
)