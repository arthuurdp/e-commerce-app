package com.ecommerce.app.util
sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(
        val message: String,
        val code: Int? = null,
        val fieldErrors: Map<String, String>? = emptyMap()
    ) : NetworkResult<Nothing>()
}
