package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.auth.*;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\r"}, d2 = {"Lcom/ecommerce/app/data/api/AuthApiService;", "", "login", "Lretrofit2/Response;", "Lcom/ecommerce/app/data/model/auth/LoginResponse;", "request", "Lcom/ecommerce/app/data/model/auth/LoginRequest;", "(Lcom/ecommerce/app/data/model/auth/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "Lcom/ecommerce/app/data/model/auth/RegisterResponse;", "Lcom/ecommerce/app/data/model/auth/RegisterRequest;", "(Lcom/ecommerce/app/data/model/auth/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerAdmin", "app_debug"})
public abstract interface AuthApiService {
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.auth.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.auth.LoginResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.auth.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.auth.RegisterResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/register/admin")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object registerAdmin(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.auth.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.auth.RegisterResponse>> $completion);
}