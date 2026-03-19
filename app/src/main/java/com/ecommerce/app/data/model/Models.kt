package com.ecommerce.app.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// ─────────────────────────────────────────────
// AUTH
// ─────────────────────────────────────────────

data class LoginRequest(
    val credential: String,
    val password: String
)

data class LoginResponse(
    val token: String
)

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val cpf: String,
    val phone: String,
    val birthDate: String,       // "yyyy-MM-dd"
    val gender: String           // "MALE" | "FEMALE" | "OTHER"
)

data class RegisterResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String
)

data class ValidationError(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: Map<String, String>
)

// ─────────────────────────────────────────────
// USER
// ─────────────────────────────────────────────

data class UserResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val cpf: String,
    val phone: String,
    val birthDate: String,
    val gender: String
)

data class UpdateUserRequest(
    val firstName: String? = null,
    val lastName: String? = null,
    val phone: String? = null,
    val gender: String? = null
)

// ─────────────────────────────────────────────
// PRODUCT
// ─────────────────────────────────────────────

@Parcelize
data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val mainImage: String?
) : Parcelable

@Parcelize
data class ProductDetailsResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val imgs: List<ProductImageResponse>,
    val weight: Double,
    val width: Int,
    val height: Int,
    val length: Int,
    val categories: List<CategoryResponse>
) : Parcelable

@Parcelize
data class ProductImageResponse(
    val id: Long,
    val url: String,
    val mainImage: Boolean
) : Parcelable

data class CreateProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val stock: Int,
    val weight: Double,
    val width: Int,
    val height: Int,
    val length: Int,
    val images: List<String>,
    val mainImageId: Long? = null,
    val categoryIds: List<Long>
)

data class UpdateProductRequest(
    val name: String? = null,
    val description: String? = null,
    val price: Double? = null,
    val stock: Int? = null,
    val weight: Double? = null,
    val width: Int? = null,
    val height: Int? = null,
    val length: Int? = null,
    val mainImageId: Long? = null,
    val imageUrls: List<String>? = null,
    val categoryIds: List<Long>? = null
)

// ─────────────────────────────────────────────
// CATEGORY
// ─────────────────────────────────────────────

@Parcelize
data class CategoryResponse(
    val id: Long,
    val name: String
) : Parcelable

data class CreateCategoryRequest(val name: String)
data class UpdateCategoryRequest(val name: String?)

// ─────────────────────────────────────────────
// CART
// ─────────────────────────────────────────────

data class CartResponse(
    val id: Long,
    val totalQuantity: Int,
    val items: List<CartItemResponse>,
    val total: Double
)

data class CartItemResponse(
    val productId: Long,
    val imageUrl: String?,
    val name: String,
    val price: Double,
    val quantity: Int,
    val subtotal: Double
)

// ─────────────────────────────────────────────
// ORDER
// ─────────────────────────────────────────────

data class OrderResponse(
    val id: Long,
    val status: String,
    val total: Double,
    val totalItems: Int,
    val createdAt: String
)

data class OrderDetailsResponse(
    val id: Long,
    val status: String,
    val total: Double,
    val createdAt: String,
    val items: List<OrderItemResponse>
)

data class OrderItemResponse(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val unitPrice: Double,
    val subtotal: Double
)

// ─────────────────────────────────────────────
// CHECKOUT
// ─────────────────────────────────────────────

data class CheckoutRequest(
    val addressId: Long,
    val paymentMethod: String   // "CREDIT_CARD" | "PIX" | "BOLETO"
)

data class CheckoutResponse(
    val orderId: Long,
    val sessionId: String,
    val checkoutUrl: String
)

// ─────────────────────────────────────────────
// ADDRESS
// ─────────────────────────────────────────────

@Parcelize
data class AddressResponse(
    val id: Long,
    val name: String,
    val street: String,
    val number: Int,
    val complement: String?,
    val neighborhood: String,
    val postalCode: String = "",
    val city: CityResponse,
    val state: StateResponse
) : Parcelable

data class CreateAddressRequest(
    val name: String,
    val street: String? = null,
    val number: Int,
    val complement: String? = null,
    val neighborhood: String? = null,
    val postalCode: String
)

data class UpdateAddressRequest(
    val name: String? = null,
    val street: String? = null,
    val number: Int? = null,
    val complement: String? = null,
    val neighborhood: String? = null,
    val postalCode: String? = null
)

data class CepLookupResponse(
    val cep: String,
    val street: String?,
    val neighborhood: String?,
    val cityId: Long,
    val cityName: String,
    val stateId: Long,
    val stateName: String,
    val stateUf: String
)

@Parcelize
data class CityResponse(
    val id: Long,
    val name: String
) : Parcelable

@Parcelize
data class StateResponse(
    val id: Long,
    val name: String,
    val uf: String
) : Parcelable

// ─────────────────────────────────────────────
// SHIPPING
// ─────────────────────────────────────────────

data class ShippingResponse(
    val id: Long,
    val orderId: Long,
    val status: String,
    val carrier: String?,
    val trackingCode: String?,
    val trackingUrl: String?,
    val shippingCost: Double?,
    val postedAt: String?,
    val deliveredAt: String?,
    val createdAt: String
)

data class FreightResponse(
    val serviceId: Int,
    val name: String,
    val price: Double,
    val deliveryDays: Int
)

// ─────────────────────────────────────────────
// PAGING WRAPPER (Spring Page<T> response)
// ─────────────────────────────────────────────

data class PageResponse<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val number: Int,        // current page (0-indexed)
    val size: Int,
    val last: Boolean,
    val first: Boolean
)

// ─────────────────────────────────────────────
// STANDARD ERROR
// ─────────────────────────────────────────────

data class StandardError(
    val timestamp: String,
    val status: Int,
    val error: String,
    val message: String
)

// ─────────────────────────────────────────────
// EMAIL / PASSWORD
// ─────────────────────────────────────────────

data class VerifyCodeRequest(val code: String)
data class ChangeEmailRequest(val email: String)
data class ChangePasswordRequest(val password: String)
data class ForgotPasswordRequest(val email: String)

data class SetPasswordRequest(val newPassword: String)
