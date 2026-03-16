package com.ecommerce.app.util

/**
 * A generic wrapper for every API call.
 *
 * Usage in a ViewModel:
 *
 *   viewModelScope.launch {
 *       _state.value = NetworkResult.Loading
 *       _state.value = repository.login(request)
 *   }
 *
 * Usage in a Fragment:
 *
 *   viewModel.loginState.observe(viewLifecycleOwner) { result ->
 *       when (result) {
 *           is NetworkResult.Loading -> showProgress()
 *           is NetworkResult.Success -> navigateToHome(result.data)
 *           is NetworkResult.Error   -> showError(result.message)
 *       }
 *   }
 */
sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val code: Int? = null) : NetworkResult<Nothing>()
}
