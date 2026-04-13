package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.user.*;
import com.ecommerce.app.data.model.util.PageResponse;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/ecommerce/app/data/api/UserApiService;", "", "deleteCurrentUser", "Lretrofit2/Response;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProfilePicture", "Lcom/ecommerce/app/data/model/user/UserResponse;", "getCurrentUser", "updateCurrentUser", "request", "Lcom/ecommerce/app/data/model/user/UpdateUserRequest;", "(Lcom/ecommerce/app/data/model/user/UpdateUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadProfilePicture", "file", "Lokhttp3/MultipartBody$Part;", "(Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
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
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "users/me/profile-picture")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadProfilePicture(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.user.UserResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "users/me/profile-picture")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteProfilePicture(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.user.UserResponse>> $completion);
}