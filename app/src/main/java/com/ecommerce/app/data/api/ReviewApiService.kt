package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.comment.CommentResponse
import com.ecommerce.app.data.model.review.AddCommentToReviewRequest
import com.ecommerce.app.data.model.review.ReviewRequest
import com.ecommerce.app.data.model.review.ReviewResponse
import com.ecommerce.app.data.model.review.UpdateReviewRequest
import retrofit2.Response
import retrofit2.http.*

interface ReviewApiService {
    @POST("products/{productId}/reviews")
    suspend fun createReview(
        @Path("productId") productId: Long,
        @Body reviewRequest: ReviewRequest
    ): Response<ReviewResponse>

    @PUT("products/reviews/{reviewId}")
    suspend fun updateReview(
        @Path("reviewId") reviewId: Long,
        @Body reviewRequest: UpdateReviewRequest
    ): Response<ReviewResponse>

    @DELETE("products/reviews/{reviewId}")
    suspend fun deleteReview(
        @Path("reviewId") reviewId: Long
    ): Response<Unit>

    @POST("products/reviews/{reviewId}/comment")
    suspend fun addCommentToReview(
        @Path("reviewId") reviewId: Long,
        @Body request: AddCommentToReviewRequest
    ): Response<CommentResponse>

    @GET("products/{productId}/reviews")
    suspend fun getProductReviews(
        @Path("productId") productId: Long
    ): Response<List<ReviewResponse>>

    @GET("products/{productId}/rating")
    suspend fun getAverageRating(
        @Path("productId") productId: Long
    ): Response<Double>
}
