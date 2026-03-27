package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.user.*
import com.ecommerce.app.data.model.util.PageResponse
import retrofit2.Response
import retrofit2.http.*

interface UserApiService {

    @GET("users/me")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PATCH("users/me")
    suspend fun updateCurrentUser(@Body request: UpdateUserRequest): Response<UserResponse>

    @DELETE("users/me")
    suspend fun deleteCurrentUser(): Response<Unit>

    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<PageResponse<UserResponse>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Long): Response<UserResponse>
}