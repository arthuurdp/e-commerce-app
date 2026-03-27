package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.UserApiService;
import com.ecommerce.app.data.model.user.UpdateUserRequest;
import com.ecommerce.app.data.model.user.UserResponse;
import com.ecommerce.app.data.model.util.PageResponse;
import com.ecommerce.app.util.NetworkResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ.\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006H\u0086@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/ecommerce/app/data/repository/UserRepository;", "Lcom/ecommerce/app/data/repository/BaseRepository;", "api", "Lcom/ecommerce/app/data/api/UserApiService;", "(Lcom/ecommerce/app/data/api/UserApiService;)V", "deleteCurrentUser", "Lcom/ecommerce/app/util/NetworkResult;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllUsers", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/user/UserResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentUser", "updateCurrentUser", "request", "Lcom/ecommerce/app/data/model/user/UpdateUserRequest;", "(Lcom/ecommerce/app/data/model/user/UpdateUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class UserRepository extends com.ecommerce.app.data.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.api.UserApiService api = null;
    
    @javax.inject.Inject()
    public UserRepository(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.UserApiService api) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCurrentUser(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.user.UpdateUserRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.user.UserResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllUsers(int page, int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.user.UserResponse>>> $completion) {
        return null;
    }
}