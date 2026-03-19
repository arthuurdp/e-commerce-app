package com.ecommerce.app.data.api

import com.ecommerce.app.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ─────────────────────────────────────────────
    // AUTH
    // ─────────────────────────────────────────────

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/register/admin")
    suspend fun registerAdmin(@Body request: RegisterRequest): Response<RegisterResponse>

    // ─────────────────────────────────────────────
    // USER
    // ─────────────────────────────────────────────

    @GET("users/me")
    suspend fun getCurrentUser(): Response<UserResponse>

    @PATCH("users/me")
    suspend fun updateCurrentUser(@Body request: UpdateUserRequest): Response<UserResponse>

    @DELETE("users/me")
    suspend fun deleteCurrentUser(): Response<Unit>

    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<PageResponse<UserResponse>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Long): Response<UserResponse>

    // ─────────────────────────────────────────────
    // PRODUCTS
    // ─────────────────────────────────────────────

    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("name") name: String? = null,
        @Query("categoryIds") categoryIds: List<Long>? = null
    ): Response<PageResponse<ProductResponse>>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<ProductDetailsResponse>

    @POST("products")
    suspend fun createProduct(@Body request: CreateProductRequest): Response<ProductDetailsResponse>

    @PATCH("products/{id}")
    suspend fun updateProduct(
        @Path("id") id: Long,
        @Body request: UpdateProductRequest
    ): Response<ProductDetailsResponse>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Long): Response<Unit>

    @PATCH("products/{id}/main-image/{mainImageId}")
    suspend fun setMainImage(
        @Path("id") id: Long,
        @Path("mainImageId") mainImageId: Long
    ): Response<ProductDetailsResponse>

    // ─────────────────────────────────────────────
    // CATEGORIES
    // ─────────────────────────────────────────────

    @GET("categories")
    suspend fun getCategories(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 50
    ): Response<PageResponse<CategoryResponse>>

    @GET("categories/{id}")
    suspend fun getCategoryById(@Path("id") id: Long): Response<CategoryResponse>

    @POST("categories")
    suspend fun createCategory(@Body request: CreateCategoryRequest): Response<CategoryResponse>

    @PATCH("categories/{id}")
    suspend fun updateCategory(
        @Path("id") id: Long,
        @Body request: UpdateCategoryRequest
    ): Response<CategoryResponse>

    @DELETE("categories/{id}")
    suspend fun deleteCategory(@Path("id") id: Long): Response<Unit>

    // ─────────────────────────────────────────────
    // CART
    // ─────────────────────────────────────────────

    @GET("cart")
    suspend fun getCart(): Response<CartResponse>

    @PATCH("cart/{productId}/increment")
    suspend fun addToCart(@Path("productId") productId: Long): Response<CartItemResponse>

    @PATCH("cart/{productId}/decrement")
    suspend fun removeFromCart(@Path("productId") productId: Long): Response<CartItemResponse?>

    @DELETE("cart")
    suspend fun clearCart(): Response<Unit>

    // ─────────────────────────────────────────────
    // CHECKOUT
    // ─────────────────────────────────────────────

    @POST("orders/checkout")
    suspend fun checkout(@Body request: CheckoutRequest): Response<CheckoutResponse>

    // ─────────────────────────────────────────────
    // ORDERS
    // ─────────────────────────────────────────────

    @GET("orders")
    suspend fun getOrders(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<PageResponse<OrderResponse>>

    @GET("orders/{id}")
    suspend fun getOrderById(@Path("id") id: Long): Response<OrderDetailsResponse>

    @GET("orders/{orderId}/shipping")
    suspend fun getShipping(@Path("orderId") orderId: Long): Response<ShippingResponse>

    // ─────────────────────────────────────────────
    // ADDRESSES
    // ─────────────────────────────────────────────

    @GET("addresses")
    suspend fun getAddresses(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<PageResponse<AddressResponse>>

    @GET("addresses/{id}")
    suspend fun getAddressById(@Path("id") id: Long): Response<AddressResponse>

    @POST("addresses")
    suspend fun createAddress(@Body request: CreateAddressRequest): Response<AddressResponse>

    @PATCH("addresses/{id}")
    suspend fun updateAddress(
        @Path("id") id: Long,
        @Body request: UpdateAddressRequest
    ): Response<AddressResponse>

    @DELETE("addresses/{id}")
    suspend fun deleteAddress(@Path("id") id: Long): Response<Unit>

    // ─────────────────────────────────────────────
    // CEP / CITIES / STATES
    // ─────────────────────────────────────────────

    @GET("cities/lookup")
    suspend fun lookupCep(@Query("cep") cep: String): Response<CepLookupResponse>

    @GET("cities")
    suspend fun getCities(
        @Query("stateId") stateId: Long,
        @Query("query") query: String,
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 20
    ): Response<PageResponse<CityResponse>>

    @GET("states")
    suspend fun getStates(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 30
    ): Response<PageResponse<StateResponse>>

    // ─────────────────────────────────────────────
    // FREIGHT
    // ─────────────────────────────────────────────

    @GET("freights")
    suspend fun calculateFreight(@Query("postalCode") postalCode: String): Response<List<FreightResponse>>

    // ─────────────────────────────────────────────
    // EMAIL / PASSWORD
    // ─────────────────────────────────────────────

    @POST("verify-email/send")
    suspend fun sendEmailVerification(): Response<Map<String, String>>

    @POST("verify-email/confirm")
    suspend fun confirmEmail(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("email/change")
    suspend fun requestEmailChange(@Body request: ChangeEmailRequest): Response<Map<String, String>>

    @POST("email/confirm")
    suspend fun confirmEmailChange(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("password/change")
    suspend fun requestPasswordChange(@Body request: ChangePasswordRequest): Response<Map<String, String>>

    @POST("password/confirm")
    suspend fun confirmPasswordChange(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("password/forgot")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<Map<String, String>>

    @POST("password/reset")
    suspend fun verifyResetCode(@Body request: VerifyCodeRequest): Response<Map<String, String>>

    @POST("password/set")
    suspend fun resetPassword(@Body request: SetPasswordRequest): Response<Map<String, String>>
}
