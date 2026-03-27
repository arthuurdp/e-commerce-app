package com.ecommerce.app.data.model.util

data class StandardError(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String
)