package com.ecommerce.app.util;

import android.util.Base64;
import org.json.JSONObject;

/**
 * Lightweight JWT decoder — no external library needed.
 * Only decodes the payload; does NOT verify the signature (the server does that).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0015\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u000e"}, d2 = {"Lcom/ecommerce/app/util/JwtDecoder;", "", "()V", "getClaim", "", "token", "claim", "getExpiration", "", "(Ljava/lang/String;)Ljava/lang/Long;", "getSubject", "getUserId", "isExpired", "", "app_debug"})
public final class JwtDecoder {
    @org.jetbrains.annotations.NotNull()
    public static final com.ecommerce.app.util.JwtDecoder INSTANCE = null;
    
    private JwtDecoder() {
        super();
    }
    
    /**
     * Returns the "sub" claim (email) from the token, or null.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSubject(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
        return null;
    }
    
    /**
     * Returns the user id embedded in the token, or null.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getUserId(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
        return null;
    }
    
    /**
     * Returns the expiration epoch (seconds), or null.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getExpiration(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
        return null;
    }
    
    /**
     * Returns true if the token has expired (compared to current system time).
     */
    public final boolean isExpired(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
        return false;
    }
    
    private final java.lang.String getClaim(java.lang.String token, java.lang.String claim) {
        return null;
    }
}