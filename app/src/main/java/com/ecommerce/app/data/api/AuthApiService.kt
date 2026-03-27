package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/register/admin")
    suspend fun registerAdmin(@Body request: RegisterRequest): Response<RegisterResponse>
}