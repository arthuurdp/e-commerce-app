package com.ecommerce.app.data.model.review

import com.ecommerce.app.data.model.comment.CommentResponse

data class ReviewResponse(
    val id: Long,
    val userId: Long,
    val userName: String,
    val userProfilePictureUrl: String?,
    val productId: Long,
    val productName: String? = null,
    val productMainImage: String? = null,
    val rating: Int,
    val comment: CommentResponse?,
    val createdAt: String
)
