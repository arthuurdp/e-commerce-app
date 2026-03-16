package com.ecommerce.app.data.repository

import com.ecommerce.app.util.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

/**
 * All repositories extend this class to get the [safeApiCall] helper,
 * which converts Retrofit responses into [NetworkResult] without boilerplate.
 */
abstract class BaseRepository {

    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Success(body)
                } else {
                    // 204 No Content or empty body — return a fake "unit" success
                    @Suppress("UNCHECKED_CAST")
                    NetworkResult.Success(Unit as T)
                }
            } else {
                val errorMessage = parseErrorBody(response)
                NetworkResult.Error(errorMessage, response.code())
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    private fun <T> parseErrorBody(response: Response<T>): String {
        return try {
            val errorJson = response.errorBody()?.string()
            if (!errorJson.isNullOrBlank()) {
                val type = object : TypeToken<Map<String, Any>>() {}.type
                val map: Map<String, Any> = Gson().fromJson(errorJson, type)
                map["message"]?.toString() ?: "Error ${response.code()}"
            } else {
                "Error ${response.code()}"
            }
        } catch (e: Exception) {
            "Error ${response.code()}"
        }
    }
}
