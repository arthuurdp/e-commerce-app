package com.ecommerce.app.ui.customer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.CategoryResponse
import com.ecommerce.app.data.model.PageResponse
import com.ecommerce.app.data.model.ProductResponse
import com.ecommerce.app.data.repository.CategoryRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    private val _categoriesState = MutableLiveData<NetworkResult<PageResponse<CategoryResponse>>>()
    val categoriesState: LiveData<NetworkResult<PageResponse<CategoryResponse>>> = _categoriesState

    private val _productsByCategory = MutableLiveData<Map<CategoryResponse, List<ProductResponse>>>()
    val productsByCategory: LiveData<Map<CategoryResponse, List<ProductResponse>>> = _productsByCategory

    fun loadCategories() {
        viewModelScope.launch {
            _categoriesState.value = NetworkResult.Loading
            _categoriesState.value = categoryRepository.getCategories()
        }
    }

    fun loadProductsByCategories(categories: List<CategoryResponse>) {
        viewModelScope.launch {
            val result = mutableMapOf<CategoryResponse, List<ProductResponse>>()
            categories.forEach { category ->
                val response = productRepository.getProducts(
                    page = 0,
                    size = 10,
                    name = null,
                    categoryIds = listOf(category.id)
                )
                if (response is NetworkResult.Success) {
                    result[category] = response.data.content
                }
            }
            _productsByCategory.value = result
        }
    }
}
