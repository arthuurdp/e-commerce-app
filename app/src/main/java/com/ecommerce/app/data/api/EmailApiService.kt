package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.email.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailApiService {

    @POST("verify-email/send")
    suspend fun sendEmailVerification(): Response<Map<String, String>>

    @POST("verify-email/confirm")
    suspend fun confirmEmail(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("email/change")
    suspend fun requestEmailChange(@Body request: ChangeEmailRequest): Response<Map<String, String>>

    @POST("email/confirm")
    suspend fun confirmEmailChange(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("password/change")
    suspend fun requestPasswordChange(@Body request: ChangePasswordRequest): Response<Map<String, String>>

    @POST("password/confirm")
    suspend fun confirmPasswordChange(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("password/forgot")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<Map<String, String>>

    @POST("password/reset")
    suspend fun verifyResetCode(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("password/set")
    suspend fun resetPassword(@Body request: SetPasswordRequest): Response<Map<String, String>>
}