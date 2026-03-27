package com.ecommerce.app.data.model.shipping

data class ShippingResponse(
    val id: Long,
    val orderId: Long,
    val status: String,
    val carrier: String?,
    val trackingCode: String?,
    val trackingUrl: String?,
    val shippingCost: Double?,
    val postedAt: String?,
    val deliveredAt: String?,
    val createdAt: String
)