package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.CartApiService;
import com.ecommerce.app.data.model.cart.CartItemResponse;
import com.ecommerce.app.data.model.cart.CartResponse;
import com.ecommerce.app.util.NetworkResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006H\u0086@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0006H\u0086@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/ecommerce/app/data/repository/CartRepository;", "Lcom/ecommerce/app/data/repository/BaseRepository;", "api", "Lcom/ecommerce/app/data/api/CartApiService;", "(Lcom/ecommerce/app/data/api/CartApiService;)V", "addToCart", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/cart/CartItemResponse;", "productId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCart", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCart", "Lcom/ecommerce/app/data/model/cart/CartResponse;", "removeFromCart", "app_debug"})
public final class CartRepository extends com.ecommerce.app.data.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.api.CartApiService api = null;
    
    @javax.inject.Inject()
    public CartRepository(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.CartApiService api) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCart(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addToCart(long productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartItemResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeFromCart(long productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.cart.CartItemResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearCart(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
}