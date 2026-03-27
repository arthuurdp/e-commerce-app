package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.AuthApiService
import com.ecommerce.app.data.model.auth.LoginRequest
import com.ecommerce.app.data.model.auth.LoginResponse
import com.ecommerce.app.data.model.auth.RegisterRequest
import com.ecommerce.app.data.model.auth.RegisterResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(private val api: AuthApiService) : BaseRepository() {
    suspend fun login(request: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall { api.login(request) }

    suspend fun register(request: RegisterRequest): NetworkResult<RegisterResponse> =
        safeApiCall { api.register(request) }

    suspend fun registerAdmin(request: RegisterRequest): NetworkResult<RegisterResponse> =
        safeApiCall { api.registerAdmin(request) }
}