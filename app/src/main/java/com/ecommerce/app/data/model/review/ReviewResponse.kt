package com.ecommerce.app.data.model.review

data class ReviewResponse(
    val id: Long,
    val userId: Long,
    val userName: String,
    val productId: Long,
    val rating: Int,
    val comment: String?,
    val createdAt: String
)
