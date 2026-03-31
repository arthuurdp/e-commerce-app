package com.ecommerce.app.ui.customer.profile.security

import android.os.Bundle
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import kotlin.Int
import kotlin.String

public class ChangeEmailEnterNewEmailFragmentDirections private constructor() {
  private data class ActionChangeEmailFragmentToEnterCodeFragment(
    public val mode: String,
    public val email: String = "",
  ) : NavDirections {
    public override val actionId: Int = R.id.action_changeEmailFragment_to_enterCodeFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("mode", this.mode)
        result.putString("email", this.email)
        return result
      }
  }

  public companion object {
    public fun actionChangeEmailFragmentToEnterCodeFragment(mode: String, email: String = ""):
        NavDirections = ActionChangeEmailFragmentToEnterCodeFragment(mode, email)
  }
}
