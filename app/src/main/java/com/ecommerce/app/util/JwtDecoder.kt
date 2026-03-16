package com.ecommerce.app.util

import android.util.Base64
import org.json.JSONObject

/**
 * Lightweight JWT decoder — no external library needed.
 * Only decodes the payload; does NOT verify the signature (the server does that).
 */
object JwtDecoder {

    /**
     * Returns the "sub" claim (email) from the token, or null.
     */
    fun getSubject(token: String): String? = getClaim(token, "sub")

    /**
     * Returns the user id embedded in the token, or null.
     */
    fun getUserId(token: String): Long? = getClaim(token, "id")?.toLongOrNull()

    /**
     * Returns the expiration epoch (seconds), or null.
     */
    fun getExpiration(token: String): Long? = getClaim(token, "exp")?.toLongOrNull()

    /**
     * Returns true if the token has expired (compared to current system time).
     */
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
