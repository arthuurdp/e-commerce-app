package com.ecommerce.app.data.repository;

import com.ecommerce.app.util.NetworkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import retrofit2.Response;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0004H\u0002J&\u0010\u0007\u001a\u00020\u00052\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0002J \u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u0002J>\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\r\"\u0004\b\u0000\u0010\u000e2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0010H\u0084@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/ecommerce/app/data/repository/BaseRepository;", "", "()V", "extractFieldErrors", "", "", "map", "extractGeneralMessage", "code", "", "parseErrorJson", "json", "safeApiCall", "Lcom/ecommerce/app/util/NetworkResult;", "T", "call", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lretrofit2/Response;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract class BaseRepository {
    
    public BaseRepository() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    protected final <T extends java.lang.Object>java.lang.Object safeApiCall(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super retrofit2.Response<T>>, ? extends java.lang.Object> call, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<? extends T>> $completion) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> parseErrorJson(java.lang.String json) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.String> extractFieldErrors(java.util.Map<java.lang.String, ? extends java.lang.Object> map) {
        return null;
    }
    
    private final java.lang.String extractGeneralMessage(java.util.Map<java.lang.String, ? extends java.lang.Object> map, int code) {
        return null;
    }
}