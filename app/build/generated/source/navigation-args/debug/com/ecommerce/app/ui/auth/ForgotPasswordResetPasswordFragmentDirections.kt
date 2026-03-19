package com.ecommerce.app.ui.auth

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class ForgotPasswordResetPasswordFragmentDirections private constructor() {
  public companion object {
    public fun actionResetPasswordFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_resetPasswordFragment_to_loginFragment)
  }
}
