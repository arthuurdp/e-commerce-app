package com.ecommerce.app.ui.customer.address

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class AddressListFragmentDirections private constructor() {
  public companion object {
    public fun actionAddressListFragmentToAddAddressFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addressListFragment_to_addAddressFragment)
  }
}
