package com.ecommerce.app.ui.auth

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToRegisterFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_registerFragment)

    public fun actionLoginFragmentToForgotPasswordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_forgotPasswordFragment)
  }
}
