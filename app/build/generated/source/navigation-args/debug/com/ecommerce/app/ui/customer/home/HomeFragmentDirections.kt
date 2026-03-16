package com.ecommerce.app.ui.customer.home

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.Long

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToProductDetailFragment(
    public val productId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_productDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("productId", this.productId)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToProductDetailFragment(productId: Long): NavDirections =
        ActionHomeFragmentToProductDetailFragment(productId)
  }
}
