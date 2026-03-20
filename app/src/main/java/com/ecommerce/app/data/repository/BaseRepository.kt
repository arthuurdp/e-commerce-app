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
                val errorBodyString = response.errorBody()?.string()
                val errorMap = parseErrorJson(errorBodyString)
                
                val code = response.code()
                val fieldErrors = extractFieldErrors(errorMap)
                val errorMessage = extractGeneralMessage(errorMap, code)
                
                NetworkResult.Error(errorMessage, code, fieldErrors)
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage ?: "Unknown error occurred")
        }
    }

    private fun parseErrorJson(json: String?): Map<String, Any>? {
        if (json.isNullOrBlank()) return null
        return try {
            val type = object : TypeToken<Map<String, Any>>() {}.type
            Gson().fromJson(json, type)
        } catch (e: Exception) {
            null
        }
    }

    private fun extractFieldErrors(map: Map<String, Any>?): Map<String, String> {
        if (map == null) return emptyMap()

        val fields = map["fields"]
        if (fields is Map<*, *>) {
            return fields.filterKeys { it is String }.mapValues { it.value.toString() } as Map<String, String>
        }

        val message = map["message"]
        if (message is Map<*, *>) {
            return message.filterKeys { it is String }.mapValues { it.value.toString() } as Map<String, String>
        }

        val errors = map["errors"]
        if (errors is List<*>) {
            val fieldMap = mutableMapOf<String, String>()
            errors.forEach { 
                if (it is Map<*, *>) {
                    val field = it["field"]?.toString()
                    val defaultMessage = it["defaultMessage"]?.toString()
                    if (field != null && defaultMessage != null) {
                        fieldMap[field] = defaultMessage
                    }
                }
            }
            if (fieldMap.isNotEmpty()) return fieldMap
        }
        
        return emptyMap()
    }

    private fun extractGeneralMessage(map: Map<String, Any>?, code: Int): String {
        if (map == null) return "Error $code"
        
        val message = map["message"]
        if (message is String && message.isNotBlank()) {
            return message
        }
        
        val error = map["error"]
        if (error is String && error.isNotBlank()) {
            return error
        }
        
        return "Error $code"
    }
}
