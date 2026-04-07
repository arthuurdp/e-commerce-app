package com.ecommerce.app.ui.customer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.cart.CartResponse
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.data.model.product.ProductResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.CartRepository
import com.ecommerce.app.data.repository.CategoryRepository
import com.ecommerce.app.data.repository.ProductRepository
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _categoriesState = MutableLiveData<NetworkResult<PageResponse<CategoryResponse>>>()
    val categoriesState: LiveData<NetworkResult<PageResponse<CategoryResponse>>> = _categoriesState

    private val _productsByCategory = MutableLiveData<Map<CategoryResponse, List<ProductResponse>>>()
    val productsByCategory: LiveData<Map<CategoryResponse, List<ProductResponse>>> = _productsByCategory

    private val _firstName = MutableLiveData<NetworkResult<String>>()
    val firstName: LiveData<NetworkResult<String>> = _firstName

    private val _cartState = MutableLiveData<NetworkResult<CartResponse>>()
    val cartState: LiveData<NetworkResult<CartResponse>> = _cartState

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadAllData()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _categoriesState.value = NetworkResult.Loading
            val result = categoryRepository.getCategories()
            _categoriesState.value = result
            if (result is NetworkResult.Success) {
                loadProductsByCategories(result.data.content)
            }
        }
    }

    fun loadCart() {
        viewModelScope.launch {
            _cartState.value = cartRepository.getCart()
        }
    }

    private suspend fun fetchFirstName() {
        val result = userRepository.getCurrentUser()
        _firstName.value = if (result is NetworkResult.Success) {
            NetworkResult.Success(result.data.firstName + "!")
        } else {
            NetworkResult.Error("Error")
        }
    }

    fun loadProductsByCategories(categories: List<CategoryResponse>) {
        viewModelScope.launch {
            val results = categories
                .map { category ->
                    async {
                        val response = productRepository.getProducts(
                            page = 0,
                            size = 10,
                            name = null,
                            categoryIds = listOf(category.id)
                        )
                        category to response
                    }
                }
                .awaitAll()

            _productsByCategory.value = results
                .filter { it.second is NetworkResult.Success }
                .associate { it.first to (it.second as NetworkResult.Success).data.content }
        }
    }

    fun loadAllData() {
        viewModelScope.launch {
            _isLoading.value = true

            // 1. Fetch categories first as products depend on them
            val categoriesDeferred = async { categoryRepository.getCategories() }
            val userDeferred = async { userRepository.getCurrentUser() }
            val cartDeferred = async { cartRepository.getCart() }

            val categoriesResult = categoriesDeferred.await()
            _categoriesState.value = categoriesResult

            // 2. If categories loaded, fetch products for each category
            if (categoriesResult is NetworkResult.Success) {
                val categories = categoriesResult.data.content
                val productsResults = categories.map { category ->
                    async {
                        val response = productRepository.getProducts(
                            page = 0, size = 10, name = null, categoryIds = listOf(category.id)
                        )
                        category to response
                    }
                }.awaitAll()

                _productsByCategory.value = productsResults
                    .filter { it.second is NetworkResult.Success }
                    .associate { it.first to (it.second as NetworkResult.Success).data.content }
            }

            // 3. Set user info
            val userResult = userDeferred.await()
            _firstName.value = if (userResult is NetworkResult.Success) {
                NetworkResult.Success(userResult.data.firstName + "!")
            } else {
                NetworkResult.Error("Error")
            }

            // 4. Set cart info
            _cartState.value = cartDeferred.await()

            _isLoading.value = false
        }
    }
}
