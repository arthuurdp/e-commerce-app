package com.ecommerce.app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.auth.LoginRequest
import com.ecommerce.app.data.model.auth.RegisterRequest
import com.ecommerce.app.data.repository.AuthRepository
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _loginState = MutableLiveData<NetworkResult<Unit>>()
    val loginState: LiveData<NetworkResult<Unit>> = _loginState

    private val _registerState = MutableLiveData<NetworkResult<Unit>>()
    val registerState: LiveData<NetworkResult<Unit>> = _registerState

    private val _forgotPasswordState = MutableLiveData<NetworkResult<String>>()
    val forgotPasswordState: LiveData<NetworkResult<String>> = _forgotPasswordState

    private val _verifyResetCodeState = MutableLiveData<NetworkResult<String>>()
    val verifyResetCodeState: LiveData<NetworkResult<String>> = _verifyResetCodeState

    private val _resetPasswordState = MutableLiveData<NetworkResult<String>>()
    val resetPasswordState: LiveData<NetworkResult<String>> = _resetPasswordState

    var isAdmin: Boolean = false
        private set

    fun login(credential: String, password: String) {
        viewModelScope.launch {
            _loginState.value = NetworkResult.Loading
            val result = authRepository.login(LoginRequest(credential, password))
            when (result) {
                is NetworkResult.Success -> {
                    val token = result.data.token
                    tokenManager.saveToken(token)

                    isAdmin = false
                    _loginState.value = NetworkResult.Success(Unit)
                }
                is NetworkResult.Error -> _loginState.value = result
                is NetworkResult.Loading -> Unit
            }
        }
    }

    fun register(request: RegisterRequest) {
        viewModelScope.launch {
            _registerState.value = NetworkResult.Loading
            val result = authRepository.register(request)
            when (result) {
                is NetworkResult.Success -> _registerState.value = NetworkResult.Success(Unit)
                is NetworkResult.Error   -> _registerState.value = result
                is NetworkResult.Loading -> Unit
            }
        }
    }

    fun logout() {
        viewModelScope.launch { tokenManager.clearToken() }
    }
}
