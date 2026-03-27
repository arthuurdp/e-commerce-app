package com.ecommerce.app.data.model.util

data class ValidationError(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: Map<String, String>
)