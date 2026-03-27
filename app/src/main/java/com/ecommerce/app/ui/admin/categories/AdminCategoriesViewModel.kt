package com.ecommerce.app.ui.admin.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.category.CategoryResponse
import com.ecommerce.app.data.model.util.PageResponse
import com.ecommerce.app.data.repository.CategoryRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminCategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _state = MutableLiveData<NetworkResult<PageResponse<CategoryResponse>>>()
    val state: LiveData<NetworkResult<PageResponse<CategoryResponse>>> = _state

    private val _actionState = MutableLiveData<NetworkResult<*>>()
    val actionState: LiveData<NetworkResult<*>> = _actionState

    fun load() {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading
            _state.value = categoryRepository.getCategories()
        }
    }

    fun create(name: String) {
        viewModelScope.launch {
            _actionState.value = NetworkResult.Loading
            _actionState.value = categoryRepository.createCategory(name)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch {
            _actionState.value = NetworkResult.Loading
            _actionState.value = categoryRepository.deleteCategory(id)
        }
    }
}