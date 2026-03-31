package com.ecommerce.app.ui.shared

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.String

public class EnterCodeFragmentDirections private constructor() {
  private data class ActionEnterCodeFragmentToResetPasswordFragment(
    public val email: String,
    public val code: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_enterCodeFragment_to_resetPasswordFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("email", this.email)
        result.putString("code", this.code)
        return result
      }
  }

  public companion object {
    public fun actionEnterCodeFragmentToResetPasswordFragment(email: String, code: String):
        NavDirections = ActionEnterCodeFragmentToResetPasswordFragment(email, code)
  }
}
