package com.ecommerce.app.ui.customer.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.model.user.UpdateUserRequest
import com.ecommerce.app.data.model.user.UserResponse
import com.ecommerce.app.data.repository.EmailRepository
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import com.ecommerce.app.util.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.get

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val emailRepository: EmailRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _profileState = MutableLiveData<NetworkResult<UserResponse>>()
    val profileState: LiveData<NetworkResult<UserResponse>> = _profileState

    private val _updateState = MutableLiveData<NetworkResult<UserResponse>>()
    val updateState: LiveData<NetworkResult<UserResponse>> = _updateState

    private val _deleteState = MutableLiveData<NetworkResult<Unit>>()
    val deleteState: LiveData<NetworkResult<Unit>> = _deleteState

    private val _sendEmailVerificationState = MutableLiveData<NetworkResult<String>>()
    val sendEmailVerificationState: LiveData<NetworkResult<String>> = _sendEmailVerificationState

    private val _confirmEmailState = MutableLiveData<NetworkResult<String>>()
    val confirmEmailState: LiveData<NetworkResult<String>> = _confirmEmailState

    private val _requestEmailChangeState = MutableLiveData<NetworkResult<String>>()
    val requestEmailChangeState: LiveData<NetworkResult<String>> = _requestEmailChangeState

    private val _confirmEmailChangeState = MutableLiveData<NetworkResult<String>>()
    val confirmEmailChangeState: LiveData<NetworkResult<String>> = _confirmEmailChangeState

    private val _requestPasswordChangeState = MutableLiveData<NetworkResult<String>>()
    val requestPasswordChangeState: LiveData<NetworkResult<String>> = _requestPasswordChangeState

    private val _confirmPasswordChangeState = MutableLiveData<NetworkResult<String>>()
    val confirmPasswordChangeState: LiveData<NetworkResult<String>> = _confirmPasswordChangeState

    private val _resetPasswordState = MutableLiveData<NetworkResult<String>>()
    val resetPasswordState: LiveData<NetworkResult<String>> = _resetPasswordState


    // ─── User ────────────────────────────────────────────────────────────────

    fun loadProfile() {
        viewModelScope.launch {
            _profileState.value = NetworkResult.Loading
            _profileState.value = userRepository.getCurrentUser()
        }
    }

    fun updateProfile(firstName: String?, lastName: String?, phone: String?) {
        viewModelScope.launch {
            _updateState.value = NetworkResult.Loading
            _updateState.value = userRepository.updateCurrentUser(
                UpdateUserRequest(firstName = firstName, lastName = lastName, phone = phone)
            )
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            _deleteState.value = NetworkResult.Loading
            _deleteState.value = userRepository.deleteCurrentUser()
        }
    }

    fun sendEmailVerification() {
        viewModelScope.launch {
            _sendEmailVerificationState.value = NetworkResult.Loading
            val result = emailRepository.sendEmailVerification()
            _sendEmailVerificationState.value = result.toStringResult()
        }
    }

    fun confirmEmail(code: String) {
        viewModelScope.launch {
            _confirmEmailState.value = NetworkResult.Loading
            val result = emailRepository.confirmEmail(code)
            _confirmEmailState.value = result.toStringResult()
        }
    }

    fun requestEmailChange(email: String) {
        viewModelScope.launch {
            _requestEmailChangeState.value = NetworkResult.Loading
            val result = emailRepository.requestEmailChange(email)
            _requestEmailChangeState.value = result.toStringResult()
        }
    }

    fun confirmEmailChange(code: String) {
        viewModelScope.launch {
            _confirmEmailChangeState.value = NetworkResult.Loading
            val result = emailRepository.confirmEmailChange(code)
            _confirmEmailChangeState.value = result.toStringResult()
        }
    }

    fun requestPasswordChange(password: String) {
        viewModelScope.launch {
            _requestPasswordChangeState.value = NetworkResult.Loading
            val result = emailRepository.requestPasswordChange(password)
            _requestPasswordChangeState.value = result.toStringResult()
        }
    }

    fun confirmPasswordChange(code: String) {
        viewModelScope.launch {
            _confirmPasswordChangeState.value = NetworkResult.Loading
            val result = emailRepository.confirmPasswordChange(code)
            _confirmPasswordChangeState.value = result.toStringResult()
        }
    }

    fun setNewPassword(newPassword: String) {
        viewModelScope.launch {
            _resetPasswordState.value = NetworkResult.Loading
            val result = emailRepository.resetPassword(newPassword)
            _resetPasswordState.value = result.toStringResult()
        }
    }

    private fun NetworkResult<Map<String, String>>.toStringResult(): NetworkResult<String> =
        when (this) {
            is NetworkResult.Success -> NetworkResult.Success(data["message"] ?: "")
            is NetworkResult.Error -> this
            is NetworkResult.Loading -> NetworkResult.Loading
        }
    fun logout() {
        viewModelScope.launch { tokenManager.clearToken() }
    }
}