package com.ecommerce.app.ui.auth

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class ForgotPasswordResetPasswordFragmentArgs(
  public val email: String,
  public val code: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("email", this.email)
    result.putString("code", this.code)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("email", this.email)
    result.set("code", this.code)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): ForgotPasswordResetPasswordFragmentArgs {
      bundle.setClassLoader(ForgotPasswordResetPasswordFragmentArgs::class.java.classLoader)
      val __email : String?
      if (bundle.containsKey("email")) {
        __email = bundle.getString("email")
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"email\" is missing and does not have an android:defaultValue")
      }
      val __code : String?
      if (bundle.containsKey("code")) {
        __code = bundle.getString("code")
        if (__code == null) {
          throw IllegalArgumentException("Argument \"code\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"code\" is missing and does not have an android:defaultValue")
      }
      return ForgotPasswordResetPasswordFragmentArgs(__email, __code)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle):
        ForgotPasswordResetPasswordFragmentArgs {
      val __email : String?
      if (savedStateHandle.contains("email")) {
        __email = savedStateHandle["email"]
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"email\" is missing and does not have an android:defaultValue")
      }
      val __code : String?
      if (savedStateHandle.contains("code")) {
        __code = savedStateHandle["code"]
        if (__code == null) {
          throw IllegalArgumentException("Argument \"code\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"code\" is missing and does not have an android:defaultValue")
      }
      return ForgotPasswordResetPasswordFragmentArgs(__email, __code)
    }
  }
}
