package com.ecommerce.app.data.model.comment

data class CommentResponse(
    val id: Long,
    val userId: Long,
    val userName: String,
    val userProfilePicture: String?,
    val productId: Long,
    val content: String,
    val createdAt: String
)
