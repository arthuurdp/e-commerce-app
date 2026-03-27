package com.ecommerce.app.data.model.user

data class UserResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val cpf: String,
    val phone: String,
    val birthDate: String,
    val gender: String
)