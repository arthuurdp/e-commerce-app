package com.ecommerce.app.ui.customer.profile.security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.app.data.repository.EmailRepository
import com.ecommerce.app.data.repository.UserRepository
import com.ecommerce.app.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SecurityViewModel @Inject constructor(
    private val emailRepository: EmailRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _deleteState = MutableLiveData<NetworkResult<Unit>>()
    val deleteState: LiveData<NetworkResult<Unit>> = _deleteState

    fun deleteAccount() {
        viewModelScope.launch {
            _deleteState.value = NetworkResult.Loading
            _deleteState.value = userRepository.deleteCurrentUser()
        }
    }

    private val _sendEmailVerificationState = MutableLiveData<NetworkResult<String>>()
    val sendEmailVerificationState: LiveData<NetworkResult<String>> = _sendEmailVerificationState

    private val _confirmEmailState = MutableLiveData<NetworkResult<String>>()
    val confirmEmailState: LiveData<NetworkResult<String>> = _confirmEmailState

    fun sendEmailVerification() {
        viewModelScope.launch {
            _sendEmailVerificationState.value = NetworkResult.Loading
            _sendEmailVerificationState.value =
                emailRepository.sendEmailVerification().toStringResult()
        }
    }

    fun confirmEmail(code: String) {
        viewModelScope.launch {
            _confirmEmailState.value = NetworkResult.Loading
            _confirmEmailState.value = emailRepository.confirmEmail(code).toStringResult()
        }
    }

    private val _requestEmailChangeState = MutableLiveData<NetworkResult<String>>()
    val requestEmailChangeState: LiveData<NetworkResult<String>> = _requestEmailChangeState

    private val _confirmEmailChangeState = MutableLiveData<NetworkResult<String>>()
    val confirmEmailChangeState: LiveData<NetworkResult<String>> = _confirmEmailChangeState

    fun requestEmailChange(email: String) {
        viewModelScope.launch {
            _requestEmailChangeState.value = NetworkResult.Loading
            _requestEmailChangeState.value = emailRepository.requestEmailChange(email).toStringResult()
        }
    }

    fun confirmEmailChange(code: String) {
        viewModelScope.launch {
            _confirmEmailChangeState.value = NetworkResult.Loading
            _confirmEmailChangeState.value =
                emailRepository.confirmEmailChange(code).toStringResult()
        }
    }

    private val _requestPasswordChangeState = MutableLiveData<NetworkResult<String>>()
    val requestPasswordChangeState: LiveData<NetworkResult<String>> = _requestPasswordChangeState

    private val _confirmPasswordChangeState = MutableLiveData<NetworkResult<String>>()
    val confirmPasswordChangeState: LiveData<NetworkResult<String>> = _confirmPasswordChangeState

    fun requestPasswordChange(password: String) {
        viewModelScope.launch {
            _requestPasswordChangeState.value = NetworkResult.Loading
            _requestPasswordChangeState.value =
                emailRepository.requestPasswordChange(password).toStringResult()
        }
    }

    fun confirmPasswordChange(code: String) {
        viewModelScope.launch {
            _confirmPasswordChangeState.value = NetworkResult.Loading
            _confirmPasswordChangeState.value =
                emailRepository.confirmPasswordChange(code).toStringResult()
        }
    }

    private val _verifyResetCodeState = MutableLiveData<NetworkResult<String>>()
    val verifyResetCodeState: LiveData<NetworkResult<String>> = _verifyResetCodeState

    fun verifyForgotPasswordCode(code: String) {
        viewModelScope.launch {
            _verifyResetCodeState.value = NetworkResult.Loading
            _verifyResetCodeState.value =
                emailRepository.verifyResetCode(code).toStringResult()
        }
    }

    private val _resetPasswordState = MutableLiveData<NetworkResult<String>>()
    val resetPasswordState: LiveData<NetworkResult<String>> = _resetPasswordState

    fun setNewPassword(newPassword: String) {
        viewModelScope.launch {
            _resetPasswordState.value = NetworkResult.Loading
            _resetPasswordState.value = emailRepository.resetPassword(newPassword).toStringResult()
        }
    }

    private fun NetworkResult<Map<String, String>>.toStringResult(): NetworkResult<String> =
        when (this) {
            is NetworkResult.Success -> NetworkResult.Success(data["message"] ?: "")
            is NetworkResult.Error -> this
            NetworkResult.Loading -> NetworkResult.Loading
        }
}