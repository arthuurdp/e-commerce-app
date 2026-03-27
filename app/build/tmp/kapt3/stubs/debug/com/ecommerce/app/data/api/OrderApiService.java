package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.order.*;
import com.ecommerce.app.data.model.shipping.*;
import com.ecommerce.app.data.model.util.PageResponse;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J.\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00032\b\b\u0003\u0010\u0016\u001a\u00020\u00172\b\b\u0003\u0010\u0018\u001a\u00020\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00032\b\b\u0001\u0010\u001c\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u001d"}, d2 = {"Lcom/ecommerce/app/data/api/OrderApiService;", "", "calculateFreight", "Lretrofit2/Response;", "", "Lcom/ecommerce/app/data/model/shipping/FreightResponse;", "postalCode", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkout", "Lcom/ecommerce/app/data/model/order/CheckoutResponse;", "request", "Lcom/ecommerce/app/data/model/order/CheckoutRequest;", "(Lcom/ecommerce/app/data/model/order/CheckoutRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrderById", "Lcom/ecommerce/app/data/model/order/OrderDetailsResponse;", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getOrders", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/order/OrderResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getShipping", "Lcom/ecommerce/app/data/model/shipping/ShippingResponse;", "orderId", "app_debug"})
public abstract interface OrderApiService {
    
    @retrofit2.http.POST(value = "orders/checkout")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkout(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.order.CheckoutRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.order.CheckoutResponse>> $completion);
    
    @retrofit2.http.GET(value = "orders")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOrders(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.order.OrderResponse>>> $completion);
    
    @retrofit2.http.GET(value = "orders/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOrderById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.order.OrderDetailsResponse>> $completion);
    
    @retrofit2.http.GET(value = "orders/{orderId}/shipping")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getShipping(@retrofit2.http.Path(value = "orderId")
    long orderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.shipping.ShippingResponse>> $completion);
    
    @retrofit2.http.GET(value = "freights")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object calculateFreight(@retrofit2.http.Query(value = "postalCode")
    @org.jetbrains.annotations.NotNull()
    java.lang.String postalCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.ecommerce.app.data.model.shipping.FreightResponse>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}