package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.review.ReviewRequest
import com.ecommerce.app.data.model.review.ReviewResponse
import retrofit2.Response
import retrofit2.http.*

interface ReviewApiService {
    @POST("products/reviews")
    suspend fun createReview(
        @Body reviewRequest: ReviewRequest
    ): Response<ReviewResponse>

    @GET("products/{productId}/reviews")
    suspend fun getProductReviews(
        @Path("productId") productId: Long
    ): Response<List<ReviewResponse>>

    @GET("products/{productId}/rating")
    suspend fun getAverageRating(
        @Path("productId") productId: Long
    ): Response<Double>
}
