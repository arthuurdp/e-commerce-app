package com.ecommerce.app.ui.customer.search

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.Long
import kotlin.String

public class SearchFragmentDirections private constructor() {
  private data class ActionSearchFragmentToProductDetailFragment(
    public val productId: Long,
    public val email: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_searchFragment_to_productDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("productId", this.productId)
        result.putString("email", this.email)
        return result
      }
  }

  public companion object {
    public fun actionSearchFragmentToProductDetailFragment(productId: Long, email: String):
        NavDirections = ActionSearchFragmentToProductDetailFragment(productId, email)
  }
}
