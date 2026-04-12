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
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath

        android.util.Log.d("AuthInterceptor", "Path: $path")

        val isPublic = publicEndpoints.any { path.contains(it) }

        android.util.Log.d("AuthInterceptor", "isPublic: $isPublic")

        if (isPublic) {
            return chain.proceed(request)
        }

        val token = runBlocking { tokenManager.getToken() }

        android.util.Log.d("AuthInterceptor", "Token: $token")

        val authenticatedRequest = request.newBuilder().apply {
            if (!token.isNullOrBlank() && token != "null") {
                addHeader("Authorization", "Bearer $token")
            }
        }.build()

        return chain.proceed(authenticatedRequest)
    }
}