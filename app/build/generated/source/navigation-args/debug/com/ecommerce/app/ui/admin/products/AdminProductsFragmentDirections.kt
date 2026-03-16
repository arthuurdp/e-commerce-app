package com.ecommerce.app.ui.admin.products

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.Long

public class AdminProductsFragmentDirections private constructor() {
  private data class ActionAdminProductsFragmentToAdminEditProductFragment(
    public val productId: Long,
  ) : NavDirections {
    public override val actionId: Int =
        R.id.action_adminProductsFragment_to_adminEditProductFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("productId", this.productId)
        return result
      }
  }

  public companion object {
    public fun actionAdminProductsFragmentToAdminEditProductFragment(productId: Long): NavDirections
        = ActionAdminProductsFragmentToAdminEditProductFragment(productId)
  }
}
