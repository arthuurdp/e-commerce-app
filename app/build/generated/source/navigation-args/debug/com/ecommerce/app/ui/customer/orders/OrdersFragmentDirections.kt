package com.ecommerce.app.ui.customer.orders

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.Long

public class OrdersFragmentDirections private constructor() {
  private data class ActionOrdersFragmentToOrderDetailFragment(
    public val orderId: Long,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_ordersFragment_to_orderDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putLong("orderId", this.orderId)
        return result
      }
  }

  public companion object {
    public fun actionOrdersFragmentToOrderDetailFragment(orderId: Long): NavDirections =
        ActionOrdersFragmentToOrderDetailFragment(orderId)
  }
}
