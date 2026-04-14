package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.comment.CommentRequest
import com.ecommerce.app.data.model.comment.CommentResponse
import retrofit2.Response
import retrofit2.http.*

interface CommentApiService {

    @POST("products/comments")
    suspend fun createComment(
        @Body request: CommentRequest
    ): Response<CommentResponse>

    @GET("products/{productId}/comments")
    suspend fun getProductComments(
        @Path("productId") productId: Long
    ): Response<List<CommentResponse>>

    @DELETE("products/comments/{commentId}")
    suspend fun deleteComment(
        @Path("commentId") commentId: Long
    ): Response<Unit>
}