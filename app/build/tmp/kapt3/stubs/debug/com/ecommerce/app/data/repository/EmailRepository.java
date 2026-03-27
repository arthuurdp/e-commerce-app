package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.EmailApiService;
import com.ecommerce.app.data.model.email.*;
import com.ecommerce.app.util.NetworkResult;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nJ(\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nJ(\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nJ(\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u000e\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nJ(\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u0011\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nJ(\u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\u0013\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nJ \u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0015J(\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/ecommerce/app/data/repository/EmailRepository;", "Lcom/ecommerce/app/data/repository/BaseRepository;", "api", "Lcom/ecommerce/app/data/api/EmailApiService;", "(Lcom/ecommerce/app/data/api/EmailApiService;)V", "confirmEmail", "Lcom/ecommerce/app/util/NetworkResult;", "", "", "code", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmEmailChange", "confirmPasswordChange", "forgotPassword", "email", "requestEmailChange", "requestPasswordChange", "password", "resetPassword", "newPassword", "sendEmailVerification", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "verifyResetCode", "app_debug"})
public final class EmailRepository extends com.ecommerce.app.data.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.api.EmailApiService api = null;
    
    @javax.inject.Inject()
    public EmailRepository(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.EmailApiService api) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sendEmailVerification(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object confirmEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object confirmEmailChange(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object confirmPasswordChange(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object forgotPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object verifyResetCode(@org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object resetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String newPassword, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends java.util.Map<java.lang.String, java.lang.String>>> $completion) {
        return null;
    }
}