package com.ecommerce.app.ui.customer.products

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class ProductDetailFragmentDirections private constructor() {
  public companion object {
    public fun actionProductDetailFragmentToCartFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_productDetailFragment_to_cartFragment)
  }
}
