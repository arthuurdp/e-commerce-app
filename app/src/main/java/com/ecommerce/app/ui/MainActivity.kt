package com.ecommerce.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.ActivityMainBinding
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNav()
        observeSession()
    }

    private fun setupBottomNav() {
        // Top-level destinations (no back arrow)
        val customerTopLevel = setOf(
            R.id.homeFragment,
            R.id.cartFragment,
            R.id.ordersFragment,
            R.id.profileFragment
        )
        val adminTopLevel = setOf(
            R.id.adminDashboardFragment,
            R.id.adminProductsFragment,
            R.id.adminOrdersFragment,
            R.id.adminUsersFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isAuthScreen = destination.id in setOf(
                R.id.loginFragment,
                R.id.registerFragment,
                R.id.forgotPasswordFragment
            )

            val isAdminScreen = destination.id in adminTopLevel ||
                    destination.parent?.id == R.id.admin_nav_graph

            when {
                isAuthScreen -> {
                    binding.bottomNavCustomer.hide()
                    binding.bottomNavAdmin.hide()
                    supportActionBar?.hide()
                }
                isAdminScreen -> {
                    binding.bottomNavCustomer.hide()
                    binding.bottomNavAdmin.show()
                    supportActionBar?.show()
                }
                else -> {
                    binding.bottomNavCustomer.show()
                    binding.bottomNavAdmin.hide()
                    supportActionBar?.show()
                }
            }
        }

        binding.bottomNavCustomer.setupWithNavController(navController)
        binding.bottomNavAdmin.setupWithNavController(navController)

        val appBarConfig = AppBarConfiguration(customerTopLevel + adminTopLevel)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun observeSession() {
        lifecycleScope.launch {
            viewModel.startDestination.collect { destination ->
                val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
                navGraph.setStartDestination(destination)
                navController.graph = navGraph
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()
}
