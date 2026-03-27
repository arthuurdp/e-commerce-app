package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.EmailApiService
import com.ecommerce.app.data.model.email.*
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmailRepository @Inject constructor(private val api: EmailApiService) : BaseRepository() {

    suspend fun sendEmailVerification(): NetworkResult<Map<String, String>> =
        safeApiCall { api.sendEmailVerification() }

    suspend fun confirmEmail(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.confirmEmail(VerifyCodeRequest(code)) }

    suspend fun requestEmailChange(email: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.requestEmailChange(ChangeEmailRequest(email)) }

    suspend fun confirmEmailChange(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.confirmEmailChange(VerifyCodeRequest(code)) }

    suspend fun requestPasswordChange(password: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.requestPasswordChange(ChangePasswordRequest(password)) }

    suspend fun confirmPasswordChange(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.confirmPasswordChange(VerifyCodeRequest(code)) }

    suspend fun forgotPassword(email: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.forgotPassword(ForgotPasswordRequest(email)) }

    suspend fun verifyResetCode(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.verifyResetCode(VerifyCodeRequest(code)) }

    suspend fun resetPassword(newPassword: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.resetPassword(SetPasswordRequest(newPassword)) }
}