package com.ecommerce.app.data.model.address

data class CreateAddressRequest(
    val name: String,
    val street: String? = null,
    val number: Int,
    val complement: String? = null,
    val neighborhood: String? = null,
    val postalCode: String
)