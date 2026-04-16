package com.ecommerce.app.data.model.review

import com.ecommerce.app.data.model.comment.CommentRequest

data class ReviewRequest(
    val productId: Long,
    val rating: Int,
    val comment: CommentRequest?
)
