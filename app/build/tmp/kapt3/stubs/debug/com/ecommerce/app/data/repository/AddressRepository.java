package com.ecommerce.app.data.repository;

import com.ecommerce.app.data.api.AddressApiService;
import com.ecommerce.app.data.model.address.*;
import com.ecommerce.app.data.model.util.PageResponse;
import com.ecommerce.app.util.NetworkResult;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00120\u0006H\u0086@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/ecommerce/app/data/repository/AddressRepository;", "Lcom/ecommerce/app/data/repository/BaseRepository;", "api", "Lcom/ecommerce/app/data/api/AddressApiService;", "(Lcom/ecommerce/app/data/api/AddressApiService;)V", "createAddress", "Lcom/ecommerce/app/util/NetworkResult;", "Lcom/ecommerce/app/data/model/address/AddressResponse;", "request", "Lcom/ecommerce/app/data/model/address/CreateAddressRequest;", "(Lcom/ecommerce/app/data/model/address/CreateAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAddress", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAddressById", "getAddresses", "Lcom/ecommerce/app/data/model/util/PageResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookupCep", "Lcom/ecommerce/app/data/model/address/CepLookupResponse;", "cep", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAddress", "Lcom/ecommerce/app/data/model/address/UpdateAddressRequest;", "(JLcom/ecommerce/app/data/model/address/UpdateAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class AddressRepository extends com.ecommerce.app.data.repository.BaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ecommerce.app.data.api.AddressApiService api = null;
    
    @javax.inject.Inject()
    public AddressRepository(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.api.AddressApiService api) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAddresses(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.util.PageResponse<com.ecommerce.app.data.model.address.AddressResponse>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAddressById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.address.AddressResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createAddress(@org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.CreateAddressRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.address.AddressResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAddress(long id, @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.address.UpdateAddressRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.address.AddressResponse>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAddress(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<kotlin.Unit>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object lookupCep(@org.jetbrains.annotations.NotNull()
    java.lang.String cep, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ecommerce.app.util.NetworkResult<com.ecommerce.app.data.model.address.CepLookupResponse>> $completion) {
        return null;
    }
}