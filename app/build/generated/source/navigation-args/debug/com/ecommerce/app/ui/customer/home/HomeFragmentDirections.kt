package com.ecommerce.app.ui.customer.home

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.Long
import kotlin.String

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToProductDetailFragment(
    public val productId: Long,
    public val email: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_productDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("productId", this.productId)
        result.putString("email", this.email)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToProductDetailFragment(productId: Long, email: String):
        NavDirections = ActionHomeFragmentToProductDetailFragment(productId, email)

    public fun actionHomeFragmentToCartFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_homeFragment_to_cartFragment)
  }
}
