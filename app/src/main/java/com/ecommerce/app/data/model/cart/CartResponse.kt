package com.ecommerce.app.data.model.cart

import com.ecommerce.app.data.model.CartItemResponse

data class CartResponse(
    val id: Long,
    val totalQuantity: Int,
    val items: List<CartItemResponse>,
    val total: Double
)