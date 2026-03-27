package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.CategoryApiService
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.data.model.category.CreateCategoryRequest
import com.ecommerce.app.data.model.category.UpdateCategoryRequest
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApiService) : BaseRepository() {

    suspend fun getCategories(): NetworkResult<PageResponse<CategoryResponse>> =
        safeApiCall { api.getCategories() }

    suspend fun createCategory(name: String): NetworkResult<CategoryResponse> =
        safeApiCall { api.createCategory(CreateCategoryRequest(name)) }

    suspend fun updateCategory(id: Long, name: String): NetworkResult<CategoryResponse> =
        safeApiCall { api.updateCategory(id, UpdateCategoryRequest(name)) }

    suspend fun deleteCategory(id: Long): NetworkResult<Unit> =
        safeApiCall { api.deleteCategory(id) }
}