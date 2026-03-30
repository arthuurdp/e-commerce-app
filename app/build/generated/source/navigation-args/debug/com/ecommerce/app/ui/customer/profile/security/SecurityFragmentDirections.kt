package com.ecommerce.app.ui.customer.profile.security

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class SecurityFragmentDirections private constructor() {
  public companion object {
    public fun actionSecurityFragmentToChangeEmailFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_securityFragment_to_changeEmailFragment)

    public fun actionSecurityFragmentToChangePasswordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_securityFragment_to_changePasswordFragment)

    public fun actionSecurityFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_securityFragment_to_loginFragment)
  }
}
