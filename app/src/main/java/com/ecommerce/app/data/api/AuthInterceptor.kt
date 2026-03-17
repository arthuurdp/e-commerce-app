package com.ecommerce.app.data.api

import com.ecommerce.app.util.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    private val publicEndpoints = listOf(
        "auth/login",
        "auth/register",
        "password/forgot",
        "password/reset"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (publicEndpoints.any { request.url.encodedPath.contains(it) }) {
            return chain.proceed(request)
        }

        val token = runBlocking { tokenManager.getToken() }

        val authenticatedRequest = request.newBuilder().apply {
            if (!token.isNullOrBlank()) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(authenticatedRequest)
    }
}