package com.ecommerce.app.ui.auth.forgot_password

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.String

public class ForgotPasswordEnterEmailFragmentDirections private constructor() {
  private data class ActionForgotPasswordFragmentToEnterCodeFragment(
    public val mode: String = "none",
    public val email: String = "",
  ) : NavDirections {
    public override val actionId: Int = R.id.action_forgotPasswordFragment_to_enterCodeFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("mode", this.mode)
        result.putString("email", this.email)
        return result
      }
  }

  public companion object {
    public fun actionForgotPasswordFragmentToEnterCodeFragment(mode: String = "none", email: String
        = ""): NavDirections = ActionForgotPasswordFragmentToEnterCodeFragment(mode, email)
  }
}
