package com.ecommerce.app.data.model.shipping

data class FreightResponse(
    val serviceId: Int,
    val name: String,
    val price: Double,
    val deliveryDays: Int
)