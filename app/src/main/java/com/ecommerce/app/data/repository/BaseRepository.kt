package com.ecommerce.app.data.repository

import com.ecommerce.app.util.NetworkResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

abstract class BaseRepository {

    protected suspend fun <T> safeApiCall(call: suspend () -> Response<T>): NetworkResult<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Success(body)
                } else {
                    @Suppress("UNCHECKED_CAST")
                    NetworkResult.Success(Unit as T)
                }
            } else {
                val errorMessage = parseErrorBody(response)
                val fieldErrors = parseFieldErrors(response)
                NetworkResult.Error(errorMessage, response.code(), fieldErrors)
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    private fun <T> parseFieldErrors(response: Response<T>): Map<String, String> {
        return try {
            val errorJson = response.errorBody()?.string()
            if (!errorJson.isNullOrBlank()) {
                val type = object : TypeToken<Map<String, Any>>() {}.type
                val map: Map<String, Any> = Gson().fromJson(errorJson, type)
                val fieldsRaw = map["fields"]
                if (fieldsRaw is Map<*, *>) {
                    @Suppress("UNCHECKED_CAST")
                    fieldsRaw as Map<String, String>
                } else emptyMap()
            } else emptyMap()
        } catch (e: Exception) {
            emptyMap()
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
