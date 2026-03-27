package com.ecommerce.app.ui.customer.products

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.String

public class ProductDetailFragmentDirections private constructor() {
  private data class ActionProductDetailFragmentToEnterCodeFragment(
    public val email: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_productDetailFragment_to_enterCodeFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("email", this.email)
        return result
      }
  }

  public companion object {
    public fun actionProductDetailFragmentToEnterCodeFragment(email: String): NavDirections =
        ActionProductDetailFragmentToEnterCodeFragment(email)
  }
}
