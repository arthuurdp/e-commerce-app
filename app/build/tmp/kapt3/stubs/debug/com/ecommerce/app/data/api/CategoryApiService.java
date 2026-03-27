package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.category.*;
import com.ecommerce.app.data.model.util.PageResponse;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ.\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000e0\u00032\b\b\u0003\u0010\u000f\u001a\u00020\u00102\b\b\u0003\u0010\u0011\u001a\u00020\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ(\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0005\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/ecommerce/app/data/api/CategoryApiService;", "", "createCategory", "Lretrofit2/Response;", "Lcom/ecommerce/app/data/model/category/CategoryResponse;", "request", "Lcom/ecommerce/app/data/model/category/CreateCategoryRequest;", "(Lcom/ecommerce/app/data/model/category/CreateCategoryRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCategory", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategories", "Lcom/ecommerce/app/data/model/util/PageResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoryById", "updateCategory", "Lcom/ecommerce/app/data/model/category/UpdateCategoryRequest;", "(JLcom/ecommerce/app/data/model/category/UpdateCategoryRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface CategoryApiService {
    
    @retrofit2.http.GET(value = "categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.category.CategoryResponse>>> $completion);
    
    @retrofit2.http.GET(value = "categories/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategoryById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.category.CategoryResponse>> $completion);
    
    @retrofit2.http.POST(value = "categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createCategory(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.category.CreateCategoryRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.category.CategoryResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "categories/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCategory(@retrofit2.http.Path(value = "id")
    long id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.category.UpdateCategoryRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.category.CategoryResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "categories/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCategory(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}