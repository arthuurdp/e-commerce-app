package com.ecommerce.app.data.model.order

data class OrderItemResponse(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val unitPrice: Double,
    val subtotal: Double
)