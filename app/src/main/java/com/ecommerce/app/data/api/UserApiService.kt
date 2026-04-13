package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.user.*
import com.ecommerce.app.data.model.util.PageResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserApiService {

    @GET("users/me")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PATCH("users/me")
    suspend fun updateCurrentUser(@Body request: UpdateUserRequest): Response<UserResponse>

    @DELETE("users/me")
    suspend fun deleteCurrentUser(): Response<Unit>

    @Multipart
    @POST("users/me/profile-picture")
    suspend fun uploadProfilePicture(@Part file: MultipartBody.Part): Response<UserResponse>

    @DELETE("users/me/profile-picture")
    suspend fun deleteProfilePicture(): Response<UserResponse>
}