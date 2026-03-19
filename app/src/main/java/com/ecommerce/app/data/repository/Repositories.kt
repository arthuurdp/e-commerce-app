package com.ecommerce.app.data.repository

import com.ecommerce.app.data.api.ApiService
import com.ecommerce.app.data.model.*
import com.ecommerce.app.util.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton

// ─────────────────────────────────────────────────────────────────────────────
// AUTH REPOSITORY
// ─────────────────────────────────────────────────────────────────────────────

@Singleton
class AuthRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun login(request: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall { api.login(request) }

    suspend fun register(request: RegisterRequest): NetworkResult<RegisterResponse> =
        safeApiCall { api.register(request) }

    suspend fun registerAdmin(request: RegisterRequest): NetworkResult<RegisterResponse> =
        safeApiCall { api.registerAdmin(request) }

    suspend fun forgotPassword(email: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.forgotPassword(ForgotPasswordRequest(email)) }

    suspend fun verifyResetCode(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.verifyResetCode(VerifyCodeRequest(code)) }

    suspend fun setPassword(newPassword: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.resetPassword(SetPasswordRequest(newPassword)) }
}

// ─────────────────────────────────────────────────────────────────────────────
// USER REPOSITORY
// ─────────────────────────────────────────────────────────────────────────────

@Singleton
class UserRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun getCurrentUser(): NetworkResult<UserResponse> =
        safeApiCall { api.getCurrentUser() }

    suspend fun updateCurrentUser(request: UpdateUserRequest): NetworkResult<UserResponse> =
        safeApiCall { api.updateCurrentUser(request) }

    suspend fun deleteCurrentUser(): NetworkResult<Unit> =
        safeApiCall { api.deleteCurrentUser() }

    suspend fun getAllUsers(page: Int = 0, size: Int = 10): NetworkResult<PageResponse<UserResponse>> =
        safeApiCall { api.getAllUsers(page, size) }

    suspend fun getUserById(id: Long): NetworkResult<UserResponse> =
        safeApiCall { api.getUserById(id) }

    suspend fun sendEmailVerification(): NetworkResult<Map<String, String>> =
        safeApiCall { api.sendEmailVerification() }

    suspend fun confirmEmail(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.confirmEmail(VerifyCodeRequest(code)) }

    suspend fun requestEmailChange(email: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.requestEmailChange(ChangeEmailRequest(email)) }

    suspend fun confirmEmailChange(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.confirmEmailChange(VerifyCodeRequest(code)) }

    suspend fun requestPasswordChange(password: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.requestPasswordChange(ChangePasswordRequest(password)) }

    suspend fun confirmPasswordChange(code: String): NetworkResult<Map<String, String>> =
        safeApiCall { api.confirmPasswordChange(VerifyCodeRequest(code)) }
}

// ─────────────────────────────────────────────────────────────────────────────
// PRODUCT REPOSITORY
// ─────────────────────────────────────────────────────────────────────────────

@Singleton
class ProductRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun getProducts(
        page: Int = 0,
        size: Int = 10,
        name: String? = null,
        categoryIds: List<Long>? = null
    ): NetworkResult<PageResponse<ProductResponse>> =
        safeApiCall { api.getProducts(page, size, name, categoryIds) }

    suspend fun getProductById(id: Long): NetworkResult<ProductDetailsResponse> =
        safeApiCall { api.getProductById(id) }

    suspend fun createProduct(request: CreateProductRequest): NetworkResult<ProductDetailsResponse> =
        safeApiCall { api.createProduct(request) }

    suspend fun updateProduct(id: Long, request: UpdateProductRequest): NetworkResult<ProductDetailsResponse> =
        safeApiCall { api.updateProduct(id, request) }

    suspend fun deleteProduct(id: Long): NetworkResult<Unit> =
        safeApiCall { api.deleteProduct(id) }
}

// ─────────────────────────────────────────────────────────────────────────────
// CATEGORY REPOSITORY
// ─────────────────────────────────────────────────────────────────────────────

@Singleton
class CategoryRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun getCategories(): NetworkResult<PageResponse<CategoryResponse>> =
        safeApiCall { api.getCategories() }

    suspend fun createCategory(name: String): NetworkResult<CategoryResponse> =
        safeApiCall { api.createCategory(CreateCategoryRequest(name)) }

    suspend fun updateCategory(id: Long, name: String): NetworkResult<CategoryResponse> =
        safeApiCall { api.updateCategory(id, UpdateCategoryRequest(name)) }

    suspend fun deleteCategory(id: Long): NetworkResult<Unit> =
        safeApiCall { api.deleteCategory(id) }
}

// ─────────────────────────────────────────────────────────────────────────────
// CART REPOSITORY
// ─────────────────────────────────────────────────────────────────────────────

@Singleton
class CartRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun getCart(): NetworkResult<CartResponse> =
        safeApiCall { api.getCart() }

    suspend fun addToCart(productId: Long): NetworkResult<CartItemResponse> =
        safeApiCall { api.addToCart(productId) }

    suspend fun removeFromCart(productId: Long): NetworkResult<CartItemResponse?> =
        safeApiCall { api.removeFromCart(productId) }

    suspend fun clearCart(): NetworkResult<Unit> =
        safeApiCall { api.clearCart() }
}

// ─────────────────────────────────────────────────────────────────────────────
// ORDER REPOSITORY
// ─────────────────────────────────────────────────────────────────────────────

@Singleton
class OrderRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun getOrders(page: Int = 0, size: Int = 10): NetworkResult<PageResponse<OrderResponse>> =
        safeApiCall { api.getOrders(page, size) }

    suspend fun getOrderById(id: Long): NetworkResult<OrderDetailsResponse> =
        safeApiCall { api.getOrderById(id) }

    suspend fun checkout(request: CheckoutRequest): NetworkResult<CheckoutResponse> =
        safeApiCall { api.checkout(request) }

    suspend fun getShipping(orderId: Long): NetworkResult<ShippingResponse> =
        safeApiCall { api.getShipping(orderId) }

    suspend fun calculateFreight(postalCode: String): NetworkResult<List<FreightResponse>> =
        safeApiCall { api.calculateFreight(postalCode) }
}

// ─────────────────────────────────────────────────────────────────────────────
// ADDRESS REPOSITORY
// ─────────────────────────────────────────────────────────────────────────────

@Singleton
class AddressRepository @Inject constructor(private val api: ApiService) : BaseRepository() {

    suspend fun getAddresses(): NetworkResult<PageResponse<AddressResponse>> =
        safeApiCall { api.getAddresses() }

    suspend fun getAddressById(id: Long): NetworkResult<AddressResponse> =
        safeApiCall { api.getAddressById(id) }

    suspend fun createAddress(request: CreateAddressRequest): NetworkResult<AddressResponse> =
        safeApiCall { api.createAddress(request) }

    suspend fun updateAddress(id: Long, request: UpdateAddressRequest): NetworkResult<AddressResponse> =
        safeApiCall { api.updateAddress(id, request) }

    suspend fun deleteAddress(id: Long): NetworkResult<Unit> =
        safeApiCall { api.deleteAddress(id) }

    suspend fun lookupCep(cep: String): NetworkResult<CepLookupResponse> =
        safeApiCall { api.lookupCep(cep) }
}
