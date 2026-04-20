package com.ecommerce.app.ui.shared

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class EnterCodeFragmentDirections private constructor() {
  public companion object {
    public fun actionEnterCodeFragmentToLoginFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_enterCodeFragment_to_loginFragment)
  }
}
