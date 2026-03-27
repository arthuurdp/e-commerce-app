package com.ecommerce.app.data.model.auth

data class RegisterResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String
)