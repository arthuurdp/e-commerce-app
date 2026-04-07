package com.ecommerce.app.util

import android.util.Base64
import org.json.JSONObject

object JwtDecoder {

    fun getExpiration(token: String): Long? = getClaim(token, "exp")?.toLongOrNull()

    fun isExpired(token: String): Boolean {
        val exp = getExpiration(token) ?: return true
        return System.currentTimeMillis() / 1000 > exp
    }

    private fun getClaim(token: String, claim: String): String? {
        return try {
            val parts = token.split(".")
            if (parts.size < 2) return null
            val payload = String(Base64.decode(parts[1], Base64.URL_SAFE or Base64.NO_PADDING))
            JSONObject(payload).optString(claim).takeIf { it.isNotEmpty() }
        } catch (e: Exception) {
            null
        }
    }
}
