package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.product.*;
import com.ecommerce.app.data.model.util.PageResponse;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJL\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u00032\b\b\u0003\u0010\u0011\u001a\u00020\u00122\b\b\u0003\u0010\u0013\u001a\u00020\u00122\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0010\b\u0003\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0017H\u00a7@\u00a2\u0006\u0002\u0010\u0018J(\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u001a\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u001bJ(\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0005\u001a\u00020\u001dH\u00a7@\u00a2\u0006\u0002\u0010\u001e\u00a8\u0006\u001f"}, d2 = {"Lcom/ecommerce/app/data/api/ProductApiService;", "", "createProduct", "Lretrofit2/Response;", "Lcom/ecommerce/app/data/model/product/ProductDetailsResponse;", "request", "Lcom/ecommerce/app/data/model/product/CreateProductRequest;", "(Lcom/ecommerce/app/data/model/product/CreateProductRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProduct", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "getProducts", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/product/ProductResponse;", "page", "", "size", "name", "", "categoryIds", "", "(IILjava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setMainImage", "mainImageId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProduct", "Lcom/ecommerce/app/data/model/product/UpdateProductRequest;", "(JLcom/ecommerce/app/data/model/product/UpdateProductRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ProductApiService {
    
    @retrofit2.http.GET(value = "products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProducts(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name, @retrofit2.http.Query(value = "categoryIds")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Long> categoryIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.product.ProductResponse>>> $completion);
    
    @retrofit2.http.GET(value = "products/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProductById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.product.ProductDetailsResponse>> $completion);
    
    @retrofit2.http.POST(value = "products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createProduct(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.product.CreateProductRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.product.ProductDetailsResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "products/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProduct(@retrofit2.http.Path(value = "id")
    long id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.product.UpdateProductRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.product.ProductDetailsResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "products/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteProduct(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.PATCH(value = "products/{id}/main-image/{mainImageId}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setMainImage(@retrofit2.http.Path(value = "id")
    long id, @retrofit2.http.Path(value = "mainImageId")
    long mainImageId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.product.ProductDetailsResponse>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}