package com.ecommerce.app.data.model.order

data class OrderResponse(
    val id: Long,
    val status: String,
    val total: Double,
    val totalItems: Int,
    val createdAt: String
)