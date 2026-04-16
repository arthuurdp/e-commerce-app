package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.comment.CommentRequest
import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.comment.UpdateCommentRequest
import retrofit2.Response
import retrofit2.http.*

interface CommentApiService {

    @PUT("products/reviews/comments/{commentId}")
    suspend fun updateComment(
        @Path("commentId") commentId: Long,
        @Body request: UpdateCommentRequest
    ): Response<CommentResponse>

    @GET("products/reviews/{productId}/comments")
    suspend fun getProductComments(
        @Path("productId") productId: Long
    ): Response<List<CommentResponse>>

    @DELETE("products/reviews/comments/{commentId}")
    suspend fun deleteComment(
        @Path("commentId") commentId: Long
    ): Response<Unit>
}