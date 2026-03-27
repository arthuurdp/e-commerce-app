package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.UserApiService
import com.ecommerce.app.data.model.user.UpdateUserRequest
import com.ecommerce.app.data.model.user.UserResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserApiService) : BaseRepository() {
    suspend fun getCurrentUser(): NetworkResult<UserResponse> =
        safeApiCall { api.getCurrentUser() }

    suspend fun updateCurrentUser(request: UpdateUserRequest): NetworkResult<UserResponse> =
        safeApiCall { api.updateCurrentUser(request) }

    suspend fun deleteCurrentUser(): NetworkResult<Unit> =
        safeApiCall { api.deleteCurrentUser() }

    suspend fun getAllUsers(page: Int = 0, size: Int = 10): NetworkResult<PageResponse<UserResponse>> =
        safeApiCall { api.getAllUsers(page, size) }
}