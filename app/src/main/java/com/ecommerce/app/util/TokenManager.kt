package com.ecommerce.app.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "auth_prefs")

@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ROLE_KEY = stringPreferencesKey("user_role")
    }

    /** Persist the JWT token returned by /auth/login */
    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    /** Retrieve the stored token (null if not logged in) */
    suspend fun getToken(): String? =
        context.dataStore.data.map { it[TOKEN_KEY] }.firstOrNull()

    /** Save the decoded role so the UI can switch between customer/admin mode */
    suspend fun saveRole(role: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_ROLE_KEY] = role
        }
    }

    suspend fun getRole(): String? =
        context.dataStore.data.map { it[USER_ROLE_KEY] }.firstOrNull()

    /** Call this on logout */
    suspend fun clearToken() {
        context.dataStore.edit { it.clear() }
    }

    /** Convenience: is any token present? */
    suspend fun isLoggedIn(): Boolean = !getToken().isNullOrBlank()
}
