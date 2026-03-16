package com.ecommerce.app.ui.customer.profile

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class ProfileFragmentDirections private constructor() {
  public companion object {
    public fun actionProfileFragmentToEditProfileFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_editProfileFragment)

    public fun actionProfileFragmentToAddressListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_addressListFragment)

    public fun actionProfileFragmentToSecurityFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_securityFragment)

    public fun actionProfileFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_profileFragment_to_loginFragment)
  }
}
