package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.CommentApiService
import com.ecommerce.app.data.model.comment.CommentRequest
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.comment.UpdateCommentRequest
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val commentApiService: CommentApiService
) : BaseRepository() {

    suspend fun createComment(productId: Long, content: String): NetworkResult<CommentResponse> {
        // Since CommentApiService no longer has createComment (it's handled by ReviewApiService or during Review creation),
        // and if it was supposed to be a standalone comment on a review, it should use reviewApiService.addCommentToReview.
        // However, looking at the backend, standalone comments without a review don't seem to exist.
        // For now, I'll return an error or we need to rethink this if it's used somewhere.
        return NetworkResult.Error("Standalone comments are not supported. Use ReviewApiService.addCommentToReview instead.")
    }

    suspend fun updateComment(commentId: Long, content: String): NetworkResult<CommentResponse> {
        return safeApiCall { commentApiService.updateComment(commentId, UpdateCommentRequest(content)) }
    }

    suspend fun getProductComments(productId: Long): NetworkResult<List<CommentResponse>> {
        return safeApiCall { commentApiService.getProductComments(productId) }
    }

    suspend fun deleteComment(commentId: Long): NetworkResult<Unit> {
        return safeApiCall { commentApiService.deleteComment(commentId) }
    }
}