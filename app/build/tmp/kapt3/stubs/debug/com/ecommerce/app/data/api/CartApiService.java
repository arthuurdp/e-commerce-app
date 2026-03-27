package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.cart.*;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\nJ \u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/ecommerce/app/data/api/CartApiService;", "", "addToCart", "Lretrofit2/Response;", "Lcom/ecommerce/app/data/model/cart/CartItemResponse;", "productId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCart", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCart", "Lcom/ecommerce/app/data/model/cart/CartResponse;", "removeFromCart", "app_debug"})
public abstract interface CartApiService {
    
    @retrofit2.http.GET(value = "cart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCart(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.cart.CartResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "cart/{productId}/increment")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addToCart(@retrofit2.http.Path(value = "productId")
    long productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.cart.CartItemResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "cart/{productId}/decrement")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeFromCart(@retrofit2.http.Path(value = "productId")
    long productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.cart.CartItemResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "cart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearCart(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
}