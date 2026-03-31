package com.ecommerce.app.ui.customer.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.CategoryRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _categoriesState = MutableLiveData<NetworkResult<PageResponse<CategoryResponse>>>()
    val categoriesState: LiveData<NetworkResult<PageResponse<CategoryResponse>>> = _categoriesState

    private val _searchState = MutableLiveData<NetworkResult<PageResponse<ProductResponse>>?>()
    val searchState: LiveData<NetworkResult<PageResponse<ProductResponse>>?> = _searchState

    private var searchJob: Job? = null
    var selectedCategoryId: Long? = null
        private set

    fun loadCategories() {
        viewModelScope.launch {
            _categoriesState.value = NetworkResult.Loading
            _categoriesState.value = categoryRepository.getCategories()
        }
    }

    fun search(query: String, categoryId: Long? = selectedCategoryId) {
        selectedCategoryId = categoryId
        searchJob?.cancel()

        if (query.isBlank() && categoryId == null) {
            _searchState.value = null
            return
        }

        searchJob = viewModelScope.launch {
            delay(350)
            _searchState.value = NetworkResult.Loading
            _searchState.value = productRepository.getProducts(
                page = 0,
                size = 40,
                name = query.trim().ifBlank { null },
                categoryIds = categoryId?.let { listOf(it) }
            )
        }
    }
    fun searchByCategory(category: CategoryResponse) {
        search(query = "", categoryId = category.id)
    }

    fun clearSearch() {
        searchJob?.cancel()
        selectedCategoryId = null
        _searchState.value = null
    }
}