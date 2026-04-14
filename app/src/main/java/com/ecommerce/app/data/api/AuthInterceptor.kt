package com.ecommerce.app.data.api

import com.ecommerce.app.util.TokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {

    private val publicGetEndpoints = listOf(
        "products",
        "categories",
        "states",
        "cities/lookup",
    )

    private val publicAnyMethodEndpoints = listOf(
        "auth/login",
        "auth/register",
        "auth/register/admin",
        "password/forgot",
        "password/reset",
        "password/set",
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath
        val method = request.method

        android.util.Log.d("AuthInterceptor", "Path: $path, Method: $method")

        val isPublic = publicAnyMethodEndpoints.any { path.contains(it) } ||
                (method == "GET" && publicGetEndpoints.any { path.contains(it) })

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