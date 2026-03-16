package com.ecommerce.app.data.api;

import com.ecommerce.app.data.model.*;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J$\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0015J*\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019J*\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019J*\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0018H\u00a7@\u00a2\u0006\u0002\u0010\u0019J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u001eH\u00a7@\u00a2\u0006\u0002\u0010\u001fJ\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\"H\u00a7@\u00a2\u0006\u0002\u0010#J\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010\u0010\u001a\u00020&H\u00a7@\u00a2\u0006\u0002\u0010\'J\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J*\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u00032\b\b\u0001\u0010\u0010\u001a\u00020.H\u00a7@\u00a2\u0006\u0002\u0010/J\u001e\u00100\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J.\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d020\u00032\b\b\u0003\u00103\u001a\u0002042\b\b\u0003\u00105\u001a\u000204H\u00a7@\u00a2\u0006\u0002\u00106J.\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u000208020\u00032\b\b\u0003\u00103\u001a\u0002042\b\b\u0003\u00105\u001a\u000204H\u00a7@\u00a2\u0006\u0002\u00106J\u0014\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0015J.\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!020\u00032\b\b\u0003\u00103\u001a\u0002042\b\b\u0003\u00105\u001a\u000204H\u00a7@\u00a2\u0006\u0002\u00106J\u001e\u0010<\u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007JB\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>020\u00032\b\b\u0001\u0010?\u001a\u00020\u00062\b\b\u0001\u0010@\u001a\u00020\f2\b\b\u0003\u00103\u001a\u0002042\b\b\u0003\u00105\u001a\u000204H\u00a7@\u00a2\u0006\u0002\u0010AJ\u0014\u0010B\u001a\b\u0012\u0004\u0012\u0002080\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J.\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F020\u00032\b\b\u0003\u00103\u001a\u0002042\b\b\u0003\u00105\u001a\u000204H\u00a7@\u00a2\u0006\u0002\u00106J\u001e\u0010G\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007JL\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I020\u00032\b\b\u0003\u00103\u001a\u0002042\b\b\u0003\u00105\u001a\u0002042\n\b\u0003\u0010J\u001a\u0004\u0018\u00010\f2\u0010\b\u0003\u0010K\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tH\u00a7@\u00a2\u0006\u0002\u0010LJ\u001e\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00032\b\b\u0001\u0010O\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J.\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020Q020\u00032\b\b\u0003\u00103\u001a\u0002042\b\b\u0003\u00105\u001a\u000204H\u00a7@\u00a2\u0006\u0002\u00106J\u001e\u0010R\u001a\b\u0012\u0004\u0012\u0002080\u00032\b\b\u0001\u0010)\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010S\u001a\b\u0012\u0004\u0012\u00020T0\u00032\b\b\u0001\u0010\u0010\u001a\u00020UH\u00a7@\u00a2\u0006\u0002\u0010VJ\u001e\u0010W\u001a\b\u0012\u0004\u0012\u00020X0\u00032\b\b\u0001\u0010Y\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010Z\u001a\b\u0012\u0004\u0012\u00020[0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\\H\u00a7@\u00a2\u0006\u0002\u0010]J\u001e\u0010^\u001a\b\u0012\u0004\u0012\u00020[0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\\H\u00a7@\u00a2\u0006\u0002\u0010]J \u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J*\u0010`\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u00032\b\b\u0001\u0010\u0010\u001a\u00020aH\u00a7@\u00a2\u0006\u0002\u0010bJ*\u0010c\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u00032\b\b\u0001\u0010\u0010\u001a\u00020dH\u00a7@\u00a2\u0006\u0002\u0010eJ*\u0010f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u00032\b\b\u0001\u0010\u0010\u001a\u00020gH\u00a7@\u00a2\u0006\u0002\u0010hJ \u0010i\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00170\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0015J(\u0010j\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010)\u001a\u00020\u00062\b\b\u0001\u0010k\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010lJ(\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\b\b\u0001\u0010)\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020nH\u00a7@\u00a2\u0006\u0002\u0010oJ(\u0010p\u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010)\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020qH\u00a7@\u00a2\u0006\u0002\u0010rJ\u001e\u0010s\u001a\b\u0012\u0004\u0012\u0002080\u00032\b\b\u0001\u0010\u0010\u001a\u00020tH\u00a7@\u00a2\u0006\u0002\u0010uJ(\u0010v\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010)\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020wH\u00a7@\u00a2\u0006\u0002\u0010x\u00a8\u0006y"}, d2 = {"Lcom/ecommerce/app/data/api/ApiService;", "", "addToCart", "Lretrofit2/Response;", "Lcom/ecommerce/app/data/model/CartItemResponse;", "productId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateFreight", "", "Lcom/ecommerce/app/data/model/FreightResponse;", "postalCode", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkout", "Lcom/ecommerce/app/data/model/CheckoutResponse;", "request", "Lcom/ecommerce/app/data/model/CheckoutRequest;", "(Lcom/ecommerce/app/data/model/CheckoutRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearCart", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmEmail", "", "Lcom/ecommerce/app/data/model/VerifyCodeRequest;", "(Lcom/ecommerce/app/data/model/VerifyCodeRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmEmailChange", "confirmPasswordChange", "createAddress", "Lcom/ecommerce/app/data/model/AddressResponse;", "Lcom/ecommerce/app/data/model/CreateAddressRequest;", "(Lcom/ecommerce/app/data/model/CreateAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCategory", "Lcom/ecommerce/app/data/model/CategoryResponse;", "Lcom/ecommerce/app/data/model/CreateCategoryRequest;", "(Lcom/ecommerce/app/data/model/CreateCategoryRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createProduct", "Lcom/ecommerce/app/data/model/ProductDetailsResponse;", "Lcom/ecommerce/app/data/model/CreateProductRequest;", "(Lcom/ecommerce/app/data/model/CreateProductRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAddress", "id", "deleteCategory", "deleteCurrentUser", "deleteProduct", "forgotPassword", "Lcom/ecommerce/app/data/model/ForgotPasswordRequest;", "(Lcom/ecommerce/app/data/model/ForgotPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAddressById", "getAddresses", "Lcom/ecommerce/app/data/model/PageResponse;", "page", "", "size", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllUsers", "Lcom/ecommerce/app/data/model/UserResponse;", "getCart", "Lcom/ecommerce/app/data/model/CartResponse;", "getCategories", "getCategoryById", "getCities", "Lcom/ecommerce/app/data/model/CityResponse;", "stateId", "query", "(JLjava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentUser", "getOrderById", "Lcom/ecommerce/app/data/model/OrderDetailsResponse;", "getOrders", "Lcom/ecommerce/app/data/model/OrderResponse;", "getProductById", "getProducts", "Lcom/ecommerce/app/data/model/ProductResponse;", "name", "categoryIds", "(IILjava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getShipping", "Lcom/ecommerce/app/data/model/ShippingResponse;", "orderId", "getStates", "Lcom/ecommerce/app/data/model/StateResponse;", "getUserById", "login", "Lcom/ecommerce/app/data/model/LoginResponse;", "Lcom/ecommerce/app/data/model/LoginRequest;", "(Lcom/ecommerce/app/data/model/LoginRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lookupCep", "Lcom/ecommerce/app/data/model/CepLookupResponse;", "cep", "register", "Lcom/ecommerce/app/data/model/RegisterResponse;", "Lcom/ecommerce/app/data/model/RegisterRequest;", "(Lcom/ecommerce/app/data/model/RegisterRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerAdmin", "removeFromCart", "requestEmailChange", "Lcom/ecommerce/app/data/model/ChangeEmailRequest;", "(Lcom/ecommerce/app/data/model/ChangeEmailRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestPasswordChange", "Lcom/ecommerce/app/data/model/ChangePasswordRequest;", "(Lcom/ecommerce/app/data/model/ChangePasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetPassword", "Lcom/ecommerce/app/data/model/ResetPasswordRequest;", "(Lcom/ecommerce/app/data/model/ResetPasswordRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEmailVerification", "setMainImage", "mainImageId", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAddress", "Lcom/ecommerce/app/data/model/UpdateAddressRequest;", "(JLcom/ecommerce/app/data/model/UpdateAddressRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCategory", "Lcom/ecommerce/app/data/model/UpdateCategoryRequest;", "(JLcom/ecommerce/app/data/model/UpdateCategoryRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCurrentUser", "Lcom/ecommerce/app/data/model/UpdateUserRequest;", "(Lcom/ecommerce/app/data/model/UpdateUserRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProduct", "Lcom/ecommerce/app/data/model/UpdateProductRequest;", "(JLcom/ecommerce/app/data/model/UpdateProductRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "auth/login")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.LoginRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.LoginResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/register")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.RegisterResponse>> $completion);
    
    @retrofit2.http.POST(value = "auth/register/admin")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object registerAdmin(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.RegisterRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.RegisterResponse>> $completion);
    
    @retrofit2.http.GET(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.UserResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCurrentUser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.UpdateUserRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.UserResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "users/me")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.GET(value = "users")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllUsers(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.UserResponse>>> $completion);
    
    @retrofit2.http.GET(value = "users/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.UserResponse>> $completion);
    
    @retrofit2.http.GET(value = "products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProducts(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name, @retrofit2.http.Query(value = "categoryIds")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Long> categoryIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.ProductResponse>>> $completion);
    
    @retrofit2.http.GET(value = "products/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getProductById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.ProductDetailsResponse>> $completion);
    
    @retrofit2.http.POST(value = "products")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createProduct(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.CreateProductRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.ProductDetailsResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "products/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateProduct(@retrofit2.http.Path(value = "id")
    long id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.UpdateProductRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.ProductDetailsResponse>> $completion);
    
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
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.ProductDetailsResponse>> $completion);
    
    @retrofit2.http.GET(value = "categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategories(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.CategoryResponse>>> $completion);
    
    @retrofit2.http.GET(value = "categories/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategoryById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CategoryResponse>> $completion);
    
    @retrofit2.http.POST(value = "categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createCategory(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.CreateCategoryRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CategoryResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "categories/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCategory(@retrofit2.http.Path(value = "id")
    long id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.UpdateCategoryRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CategoryResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "categories/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteCategory(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.GET(value = "cart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCart(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CartResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "cart/{productId}/increment")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addToCart(@retrofit2.http.Path(value = "productId")
    long productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CartItemResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "cart/{productId}/decrement")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeFromCart(@retrofit2.http.Path(value = "productId")
    long productId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CartItemResponse>> $completion);
    
    @retrofit2.http.DELETE(value = "cart")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object clearCart(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.POST(value = "orders/checkout")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkout(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.CheckoutRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CheckoutResponse>> $completion);
    
    @retrofit2.http.GET(value = "orders")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOrders(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.OrderResponse>>> $completion);
    
    @retrofit2.http.GET(value = "orders/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getOrderById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.OrderDetailsResponse>> $completion);
    
    @retrofit2.http.GET(value = "orders/{orderId}/shipping")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getShipping(@retrofit2.http.Path(value = "orderId")
    long orderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.ShippingResponse>> $completion);
    
    @retrofit2.http.GET(value = "addresses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAddresses(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.AddressResponse>>> $completion);
    
    @retrofit2.http.GET(value = "addresses/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAddressById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.AddressResponse>> $completion);
    
    @retrofit2.http.POST(value = "addresses")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createAddress(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.CreateAddressRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.AddressResponse>> $completion);
    
    @retrofit2.http.PATCH(value = "addresses/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateAddress(@retrofit2.http.Path(value = "id")
    long id, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.UpdateAddressRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.AddressResponse>> $completion);
    
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
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.CepLookupResponse>> $completion);
    
    @retrofit2.http.GET(value = "cities")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCities(@retrofit2.http.Query(value = "stateId")
    long stateId, @retrofit2.http.Query(value = "query")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.CityResponse>>> $completion);
    
    @retrofit2.http.GET(value = "states")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStates(@retrofit2.http.Query(value = "page")
    int page, @retrofit2.http.Query(value = "size")
    int size, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.ecommerce.app.data.model.PageResponse<com.ecommerce.app.data.model.StateResponse>>> $completion);
    
    @retrofit2.http.GET(value = "freights")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object calculateFreight(@retrofit2.http.Query(value = "postalCode")
    @org.jetbrains.annotations.NotNull()
    java.lang.String postalCode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.ecommerce.app.data.model.FreightResponse>>> $completion);
    
    @retrofit2.http.POST(value = "verify-email/send")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendEmailVerification(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "verify-email/confirm")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object confirmEmail(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "email/change")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object requestEmailChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.ChangeEmailRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "email/confirm")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object confirmEmailChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/change")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object requestPasswordChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.ChangePasswordRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/confirm")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object confirmPasswordChange(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.VerifyCodeRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/forgot")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object forgotPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.ForgotPasswordRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @retrofit2.http.POST(value = "password/reset")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object resetPassword(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.ecommerce.app.data.model.ResetPasswordRequest request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.Map<java.lang.String, java.lang.String>>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}