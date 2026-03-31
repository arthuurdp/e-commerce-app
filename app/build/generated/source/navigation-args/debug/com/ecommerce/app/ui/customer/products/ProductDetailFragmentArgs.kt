package com.ecommerce.app.ui.customer.products

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Long
import kotlin.String
import kotlin.jvm.JvmStatic

public data class ProductDetailFragmentArgs(
  public val productId: Long,
  public val email: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putLong("productId", this.productId)
    result.putString("email", this.email)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("productId", this.productId)
    result.set("email", this.email)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ProductDetailFragmentArgs {
      bundle.setClassLoader(ProductDetailFragmentArgs::class.java.classLoader)
      val __productId : Long
      if (bundle.containsKey("productId")) {
        __productId = bundle.getLong("productId")
      } else {
        throw IllegalArgumentException("Required argument \"productId\" is missing and does not have an android:defaultValue")
      }
      val __email : String?
      if (bundle.containsKey("email")) {
        __email = bundle.getString("email")
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"email\" is missing and does not have an android:defaultValue")
      }
      return ProductDetailFragmentArgs(__productId, __email)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): ProductDetailFragmentArgs {
      val __productId : Long?
      if (savedStateHandle.contains("productId")) {
        __productId = savedStateHandle["productId"]
        if (__productId == null) {
          throw IllegalArgumentException("Argument \"productId\" of type long does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"productId\" is missing and does not have an android:defaultValue")
      }
      val __email : String?
      if (savedStateHandle.contains("email")) {
        __email = savedStateHandle["email"]
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"email\" is missing and does not have an android:defaultValue")
      }
      return ProductDetailFragmentArgs(__productId, __email)
    }
  }
}
