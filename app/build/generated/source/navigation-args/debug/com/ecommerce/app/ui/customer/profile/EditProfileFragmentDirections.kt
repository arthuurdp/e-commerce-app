package com.ecommerce.app.ui.customer.profile

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class EditProfileFragmentDirections private constructor() {
  public companion object {
    public fun actionEditProfileFragmentToChangeEmailFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_editProfileFragment_to_changeEmailFragment)

    public fun actionEditProfileFragmentToChangePasswordFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_editProfileFragment_to_changePasswordFragment)
  }
}
