package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.user.*;
import com.ecommerce.app.data.model.util.PageResponse;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J.\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00032\b\b\u0003\u0010\t\u001a\u00020\n2\b\b\u0003\u0010\u000b\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/ecommerce/app/data/api/UserApiService;", "", "deleteCurrentUser", "Lretrofit2/Response;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllUsers", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/user/UserResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentUser", "getUserById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCurrentUser", "request", "Lcom/ecommerce/app/data/model/user/UpdateUserRequest;", "(Lcom/ecommerce/app/data/model/user/UpdateUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface UserApiService {
    
    @retrofit2.http.GET(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.user.UserResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCurrentUser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.user.UpdateUserRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.user.UserResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.GET(value = "users")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllUsers(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.user.UserResponse>>> $completion);
    
    @retrofit2.http.GET(value = "users/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.user.UserResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}