package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.CommentApiService
import com.ecommerce.app.data.model.comment.CommentRequest
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val commentApiService: CommentApiService
) : BaseRepository() {

    suspend fun createComment(productId: Long, content: String): NetworkResult<CommentResponse> {
        return safeApiCall { commentApiService.createComment(CommentRequest(productId, content)) }
    }

    suspend fun getProductComments(productId: Long): NetworkResult<List<CommentResponse>> {
        return safeApiCall { commentApiService.getProductComments(productId) }
    }

    suspend fun deleteComment(commentId: Long): NetworkResult<Unit> {
        return safeApiCall { commentApiService.deleteComment(commentId) }
    }
}