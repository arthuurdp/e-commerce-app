package com.ecommerce.app.ui

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import com.ecommerce.app.R
import com.ecommerce.app.databinding.ActivityMainBinding
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController
    private var isKeyboardVisible = false
    private var bottomNavSetup = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavCustomer.hide()
        setupKeyboardListener()
        observeSession()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        navController.handleDeepLink(intent)
    }

    private fun observeSession() {
        lifecycleScope.launch {
            viewModel.isLoggedIn.collect { isLoggedIn ->
                isLoggedIn ?: return@collect
                if (isLoggedIn) showMainApp() else showAuth()
            }
        }
    }

    private fun showAuth() {
        binding.bottomNavCustomer.hide()
        if (navController.currentDestination?.parent?.id != R.id.nav_graph_auth) {
            navController.navigate(
                R.id.nav_graph_auth,
                null,
                navOptions {
                    popUpTo(R.id.nav_graph_main) { inclusive = true }
                }
            )
        }
    }

    fun showMainApp() {
        binding.bottomNavCustomer.show()
        if (navController.currentDestination?.parent?.id == R.id.nav_graph_auth) {
            navController.navigate(
                R.id.nav_graph_home,
                null,
                navOptions {
                    popUpTo(R.id.nav_graph_auth) { inclusive = true }
                    launchSingleTop = true
                }
            )
        }
        if (!bottomNavSetup) {
            setupBottomNav()
            bottomNavSetup = true
        }
    }

    private fun setupBottomNav() {
        binding.bottomNavCustomer.setOnItemSelectedListener { item ->
            val currentDestParent = navController.currentDestination?.parent?.id

            val tabAnimOptions = navOptions {
                anim {
                    enter = R.anim.fade_in
                    exit = R.anim.fade_out
                    popEnter = R.anim.fade_in
                    popExit = R.anim.fade_out
                }
                launchSingleTop = true
                restoreState = true
            }

            when (item.itemId) {
                R.id.nav_graph_home -> {
                    if (currentDestParent != R.id.nav_graph_home) {
                        navController.navigate(
                            R.id.nav_graph_home,
                            null,
                            navOptions {
                                anim {
                                    enter = R.anim.fade_in
                                    exit = R.anim.fade_out
                                    popEnter = R.anim.fade_in
                                    popExit = R.anim.fade_out
                                }
                                popUpTo(R.id.nav_graph_main) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                    }
                    true
                }
                R.id.nav_graph_search -> {
                    if (currentDestParent != R.id.nav_graph_search) {
                        navController.navigate(
                            R.id.nav_graph_search,
                            null,
                            navOptions {
                                anim {
                                    enter = R.anim.fade_in
                                    exit = R.anim.fade_out
                                    popEnter = R.anim.fade_in
                                    popExit = R.anim.fade_out
                                }
                                popUpTo(R.id.nav_graph_main) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                    }
                    true
                }
                R.id.nav_graph_profile -> {
                    if (currentDestParent != R.id.nav_graph_profile) {
                        navController.navigate(
                            R.id.nav_graph_profile,
                            null,
                            navOptions {
                                anim {
                                    enter = R.anim.fade_in
                                    exit = R.anim.fade_out
                                    popEnter = R.anim.fade_in
                                    popExit = R.anim.fade_out
                                }
                                popUpTo(R.id.nav_graph_main) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                    }
                    true
                }
                else -> false
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val parentId = destination.parent?.id
            val itemId = when (parentId) {
                R.id.nav_graph_home -> R.id.nav_graph_home
                R.id.nav_graph_search -> R.id.nav_graph_search
                R.id.nav_graph_profile -> R.id.nav_graph_profile
                else -> null
            }
            itemId?.let {
                if (binding.bottomNavCustomer.selectedItemId != it) {
                    binding.bottomNavCustomer.selectedItemId = it
                }
                val isRootDestination = destination.id in setOf(
                    R.id.homeFragment, R.id.searchFragment, R.id.profileFragment
                )
                if (isRootDestination && !isKeyboardVisible) {
                    binding.bottomNavCustomer.show()
                } else {
                    binding.bottomNavCustomer.hide()
                }
            }
            if (parentId == R.id.nav_graph_auth) {
                binding.bottomNavCustomer.hide()
            }
        }
    }

    private fun setupKeyboardListener() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            binding.root.getWindowVisibleDisplayFrame(r)
            val screenHeight = binding.root.rootView.height
            val keypadHeight = screenHeight - r.bottom
            val currentlyVisible = keypadHeight > screenHeight * 0.15

            if (currentlyVisible != isKeyboardVisible) {
                isKeyboardVisible = currentlyVisible
                if (isKeyboardVisible) binding.bottomNavCustomer.hide()
                else if (navController.currentDestination?.parent?.id != R.id.nav_graph_auth) {
                    binding.bottomNavCustomer.show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp() || super.onSupportNavigateUp()
}