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

    init {
        loadFirstName()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _categoriesState.value = NetworkResult.Loading
            _categoriesState.value = categoryRepository.getCategories()
        }
    }

    fun loadCart() {
        viewModelScope.launch {
            _cartState.value = cartRepository.getCart()
        }
    }

    private fun loadFirstName() {
        viewModelScope.launch {
            val result = userRepository.getCurrentUser()
            _firstName.value = if (result is NetworkResult.Success) {
                NetworkResult.Success(result.data.firstName + "!")
            } else {
                result as NetworkResult<String>
            }
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
                .filterIsInstance<Pair<CategoryResponse, NetworkResult.Success<PageResponse<ProductResponse>>>>()
                .associate { (category, result) -> category to result.data.content }
        }
    }
}