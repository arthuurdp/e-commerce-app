package com.ecommerce.app.data.model.user

data class UpdateUserRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val phone: String? = null,
    val gender: String? = null
)