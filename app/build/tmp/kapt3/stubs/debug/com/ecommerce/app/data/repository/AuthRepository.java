package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.AuthApiService;
import com.ecommerce.app.data.model.auth.LoginRequest;
import com.ecommerce.app.data.model.auth.LoginResponse;
import com.ecommerce.app.data.model.auth.RegisterRequest;
import com.ecommerce.app.data.model.auth.RegisterResponse;
import com.ecommerce.app.util.NetworkResult;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\b\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\b\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/ecommerce/app/data/repository/AuthRepository;", "Lcom/ecommerce/app/data/repository/BaseRepository;", "api", "Lcom/ecommerce/app/data/api/AuthApiService;", "(Lcom/ecommerce/app/data/api/AuthApiService;)V", "login", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/auth/LoginResponse;", "request", "Lcom/ecommerce/app/data/model/auth/LoginRequest;", "(Lcom/ecommerce/app/data/model/auth/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/ecommerce/app/data/model/auth/RegisterResponse;", "Lcom/ecommerce/app/data/model/auth/RegisterRequest;", "(Lcom/ecommerce/app/data/model/auth/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerAdmin", "app_debug"})
public final class AuthRepository extends com.ecommerce.app.data.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.api.AuthApiService api = null;
    
    @javax.inject.Inject()
    public AuthRepository(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.AuthApiService api) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object login(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.auth.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.auth.LoginResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object register(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.auth.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.auth.RegisterResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object registerAdmin(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.auth.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.auth.RegisterResponse>> $completion) {
        return null;
    }
}