package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.OrderApiService;
import com.ecommerce.app.data.model.order.*;
import com.ecommerce.app.data.model.util.PageResponse;
import com.ecommerce.app.util.NetworkResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ.\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/ecommerce/app/data/repository/OrderRepository;", "Lcom/ecommerce/app/data/repository/BaseRepository;", "api", "Lcom/ecommerce/app/data/api/OrderApiService;", "(Lcom/ecommerce/app/data/api/OrderApiService;)V", "checkout", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/order/CheckoutResponse;", "request", "Lcom/ecommerce/app/data/model/order/CheckoutRequest;", "(Lcom/ecommerce/app/data/model/order/CheckoutRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrderById", "Lcom/ecommerce/app/data/model/order/OrderDetailsResponse;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrders", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/order/OrderResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class OrderRepository extends com.ecommerce.app.data.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.api.OrderApiService api = null;
    
    @javax.inject.Inject()
    public OrderRepository(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.OrderApiService api) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getOrders(int page, int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.order.OrderResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getOrderById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.order.OrderDetailsResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object checkout(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.order.CheckoutRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.order.CheckoutResponse>> $completion) {
        return null;
    }
}