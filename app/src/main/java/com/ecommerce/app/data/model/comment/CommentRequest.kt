package com.ecommerce.app.data.model.comment

data class CommentRequest(
    val productId: Long,
    val content: String
)
