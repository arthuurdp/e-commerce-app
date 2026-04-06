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
        "auth/register/admin",
        "password/forgot",
        "password/reset",
        "password/set",
        "products",
        "categories",
        "states",
        "freights"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        val isPublic = publicEndpoints.any { path.contains(it) }

        if (isPublic) {
            return chain.proceed(request)
        }

        val token = runBlocking { tokenManager.getToken() }

        val authenticatedRequest = request.newBuilder().apply {
            if (!token.isNullOrBlank() && token != "null") {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(authenticatedRequest)
    }
}