package com.ecommerce.app.ui.auth

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_homeFragment)

    public fun actionLoginFragmentToRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_registerFragment)

    public fun actionLoginFragmentToForgotPasswordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_forgotPasswordFragment)

    public fun actionLoginFragmentToAdminNavGraph(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_admin_nav_graph)
  }
}
