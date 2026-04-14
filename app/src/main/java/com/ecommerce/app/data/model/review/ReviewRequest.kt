package com.ecommerce.app.data.model.review

data class ReviewRequest(
    val productId: Long,
    val rating: Int,
    val comment: String?
)
