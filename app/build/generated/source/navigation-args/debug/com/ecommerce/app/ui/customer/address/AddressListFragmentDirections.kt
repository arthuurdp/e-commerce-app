package com.ecommerce.app.ui.customer.address

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R
import com.ecommerce.app.`data`.model.address.AddressResponse
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class AddressListFragmentDirections private constructor() {
  private data class ActionAddressListFragmentToEditAddressFragment(
    public val address: AddressResponse,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_addressListFragment_to_editAddressFragment

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(AddressResponse::class.java)) {
          result.putParcelable("address", this.address as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(AddressResponse::class.java)) {
          result.putSerializable("address", this.address as Serializable)
        } else {
          throw UnsupportedOperationException(AddressResponse::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionAddressListFragmentToAddAddressFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addressListFragment_to_addAddressFragment)

    public fun actionAddressListFragmentToEditAddressFragment(address: AddressResponse):
        NavDirections = ActionAddressListFragmentToEditAddressFragment(address)
  }
}
