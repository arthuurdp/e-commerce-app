package com.ecommerce.app.data.model.auth

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val birthDate: String,
    val gender: String
)