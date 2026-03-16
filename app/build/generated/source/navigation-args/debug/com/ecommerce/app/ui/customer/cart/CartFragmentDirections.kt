package com.ecommerce.app.ui.customer.cart

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class CartFragmentDirections private constructor() {
  public companion object {
    public fun actionCartFragmentToCheckoutFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_cartFragment_to_checkoutFragment)
  }
}
