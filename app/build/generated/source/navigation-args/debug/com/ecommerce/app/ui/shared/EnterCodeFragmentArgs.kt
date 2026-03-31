package com.ecommerce.app.ui.shared

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class EnterCodeFragmentArgs(
  public val mode: String = "none",
  public val email: String = "",
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("mode", this.mode)
    result.putString("email", this.email)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("mode", this.mode)
    result.set("email", this.email)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): EnterCodeFragmentArgs {
      bundle.setClassLoader(EnterCodeFragmentArgs::class.java.classLoader)
      val __mode : String?
      if (bundle.containsKey("mode")) {
        __mode = bundle.getString("mode")
        if (__mode == null) {
          throw IllegalArgumentException("Argument \"mode\" is marked as non-null but was passed a null value.")
        }
      } else {
        __mode = "none"
      }
      val __email : String?
      if (bundle.containsKey("email")) {
        __email = bundle.getString("email")
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value.")
        }
      } else {
        __email = ""
      }
      return EnterCodeFragmentArgs(__mode, __email)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): EnterCodeFragmentArgs {
      val __mode : String?
      if (savedStateHandle.contains("mode")) {
        __mode = savedStateHandle["mode"]
        if (__mode == null) {
          throw IllegalArgumentException("Argument \"mode\" is marked as non-null but was passed a null value")
        }
      } else {
        __mode = "none"
      }
      val __email : String?
      if (savedStateHandle.contains("email")) {
        __email = savedStateHandle["email"]
        if (__email == null) {
          throw IllegalArgumentException("Argument \"email\" is marked as non-null but was passed a null value")
        }
      } else {
        __email = ""
      }
      return EnterCodeFragmentArgs(__mode, __email)
    }
  }
}
