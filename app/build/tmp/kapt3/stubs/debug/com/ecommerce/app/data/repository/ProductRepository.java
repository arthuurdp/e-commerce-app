package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.ProductApiService;
import com.ecommerce.app.data.model.product.CreateProductRequest;
import com.ecommerce.app.data.model.product.ProductDetailsResponse;
import com.ecommerce.app.data.model.product.ProductResponse;
import com.ecommerce.app.data.model.product.UpdateProductRequest;
import com.ecommerce.app.data.model.util.PageResponse;
import com.ecommerce.app.util.NetworkResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJL\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ$\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/ecommerce/app/data/repository/ProductRepository;", "Lcom/ecommerce/app/data/repository/BaseRepository;", "api", "Lcom/ecommerce/app/data/api/ProductApiService;", "(Lcom/ecommerce/app/data/api/ProductApiService;)V", "createProduct", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/product/ProductDetailsResponse;", "request", "Lcom/ecommerce/app/data/model/product/CreateProductRequest;", "(Lcom/ecommerce/app/data/model/product/CreateProductRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProduct", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "getProducts", "Lcom/ecommerce/app/data/model/util/PageResponse;", "Lcom/ecommerce/app/data/model/product/ProductResponse;", "page", "", "size", "name", "", "categoryIds", "", "(IILjava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProduct", "Lcom/ecommerce/app/data/model/product/UpdateProductRequest;", "(JLcom/ecommerce/app/data/model/product/UpdateProductRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ProductRepository extends com.ecommerce.app.data.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.api.ProductApiService api = null;
    
    @javax.inject.Inject()
    public ProductRepository(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.ProductApiService api) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getProducts(int page, int size, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Long> categoryIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.product.ProductResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getProductById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createProduct(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.product.CreateProductRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateProduct(long id, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.product.UpdateProductRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.product.ProductDetailsResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteProduct(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
}