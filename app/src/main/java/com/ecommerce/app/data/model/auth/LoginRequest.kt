package com.ecommerce.app.data.model.auth

data class LoginRequest(
    val credential: String,
    val password: String
)