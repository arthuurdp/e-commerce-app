package com.ecommerce.app.ui.auth

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class ForgotPasswordFragmentDirections private constructor() {
  public companion object {
    public fun actionForgotPasswordFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_forgotPasswordFragment_to_loginFragment)
  }
}
