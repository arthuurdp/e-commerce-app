package com.ecommerce.app.ui.admin.dashboard

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.ecommerce.app.R

public class AdminDashboardFragmentDirections private constructor() {
  public companion object {
    public fun actionAdminDashboardFragmentToAdminProductsFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminProductsFragment)

    public fun actionAdminDashboardFragmentToAdminOrdersFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminOrdersFragment)

    public fun actionAdminDashboardFragmentToAdminUsersFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminUsersFragment)

    public fun actionAdminDashboardFragmentToAdminCategoriesFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_adminDashboardFragment_to_adminCategoriesFragment)
  }
}
