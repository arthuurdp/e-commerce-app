package com.ecommerce.app.data.api

import com.ecommerce.app.util.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * OkHttp interceptor that reads the stored JWT token from DataStore
 * and attaches it as a Bearer header to every outgoing request.
 *
 * Endpoints that don't need auth (login, register, forgot-password…)
 * will simply get an empty Authorization header, which the server ignores.
 */
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenManager.getToken() }

        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrBlank()) {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(request)
    }
}
