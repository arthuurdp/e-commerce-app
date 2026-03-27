package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.address.*;
import com.ecommerce.app.data.model.util.PageResponse;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ.\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000f0\u00032\b\b\u0003\u0010\u0010\u001a\u00020\u00112\b\b\u0003\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013JB\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u000f0\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u000b2\b\b\u0001\u0010\u0017\u001a\u00020\u00182\b\b\u0003\u0010\u0010\u001a\u00020\u00112\b\b\u0003\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0019J.\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000f0\u00032\b\b\u0003\u0010\u0010\u001a\u00020\u00112\b\b\u0003\u0010\u0012\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\b\b\u0001\u0010\u001e\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u001fJ(\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0005\u001a\u00020!H\u00a7@\u00a2\u0006\u0002\u0010\"\u00a8\u0006#"}, d2 = {"Lcom/ecommerce/app/data/api/AddressApiService;", "", "createAddress", "Lretrofit2/Response;", "Lcom/ecommerce/app/data/model/address/AddressResponse;", "request", "Lcom/ecommerce/app/data/model/address/CreateAddressRequest;", "(Lcom/ecommerce/app/data/model/address/CreateAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAddress", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAddressById", "getAddresses", "Lcom/ecommerce/app/data/model/util/PageResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCities", "Lcom/ecommerce/app/data/model/address/CityResponse;", "stateId", "query", "", "(JLjava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStates", "Lcom/ecommerce/app/data/model/address/StateResponse;", "lookupCep", "Lcom/ecommerce/app/data/model/address/CepLookupResponse;", "cep", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAddress", "Lcom/ecommerce/app/data/model/address/UpdateAddressRequest;", "(JLcom/ecommerce/app/data/model/address/UpdateAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AddressApiService {
    
    @retrofit2.http.GET(value = "addresses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAddresses(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.address.AddressResponse>>> $completion);
    
    @retrofit2.http.GET(value = "addresses/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAddressById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.address.AddressResponse>> $completion);
    
    @retrofit2.http.POST(value = "addresses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createAddress(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.CreateAddressRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.address.AddressResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "addresses/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAddress(@retrofit2.http.Path(value = "id")
    long id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.UpdateAddressRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.address.AddressResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "addresses/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAddress(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.GET(value = "cities/lookup")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object lookupCep(@retrofit2.http.Query(value = "cep")
    @org.jetbrains.annotations.NotNull()
    java.lang.String cep, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.address.CepLookupResponse>> $completion);
    
    @retrofit2.http.GET(value = "cities")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCities(@retrofit2.http.Query(value = "stateId")
    long stateId, @retrofit2.http.Query(value = "query")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.address.CityResponse>>> $completion);
    
    @retrofit2.http.GET(value = "states")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStates(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.address.StateResponse>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}