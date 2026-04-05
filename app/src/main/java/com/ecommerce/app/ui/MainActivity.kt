package com.ecommerce.app.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ecommerce.app.R
import com.ecommerce.app.databinding.ActivityMainBinding
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainViewModel by viewModels()
    private var isKeyboardVisible = false

    private val customerRootDestinations = setOf(
        R.id.homeFragment,
        R.id.searchFragment,
        R.id.profileFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNav()
        observeSession()
        setupKeyboardListener()
    }

    private fun setupBottomNav() {

        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleBottomNavVisibility(destination.id)
            updateBottomNavScale(binding.bottomNavCustomer, destination.id)

            if (destination.id in customerRootDestinations) {
                binding.bottomNavCustomer.menu.findItem(destination.id)?.isChecked = true
            }
        }

        binding.bottomNavCustomer.setOnItemSelectedListener { item ->
            val currentId = navController.currentDestination?.id

            if (currentId == item.itemId) return@setOnItemSelectedListener true

            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(
                        R.id.homeFragment,
                        null,
                        androidx.navigation.NavOptions.Builder()
                            .setPopUpTo(R.id.homeFragment, inclusive = false, saveState = false)
                            .setLaunchSingleTop(true)
                            .build()
                    )
                    true
                }
                R.id.searchFragment -> {
                    navController.navigate(
                        R.id.searchFragment,
                        null,
                        androidx.navigation.NavOptions.Builder()
                            .setPopUpTo(R.id.homeFragment, inclusive = false, saveState = true)
                            .setLaunchSingleTop(true)
                            .setRestoreState(true)
                            .build()
                    )
                    true
                }
                R.id.profileFragment -> {
                    navController.navigate(
                        R.id.profileFragment,
                        null,
                        androidx.navigation.NavOptions.Builder()
                            .setPopUpTo(R.id.homeFragment, inclusive = false, saveState = true)
                            .setLaunchSingleTop(true)
                            .setRestoreState(true)
                            .build()
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun handleBottomNavVisibility(currentDestinationId: Int) {
        val isRootDestination = currentDestinationId in customerRootDestinations
        if (isRootDestination && !isKeyboardVisible) {
            binding.bottomNavCustomer.show()
        } else {
            binding.bottomNavCustomer.hide()
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
                navController.currentDestination?.id?.let { handleBottomNavVisibility(it) }
            }
        }
    }

    private fun updateBottomNavScale(navView: BottomNavigationView, selectedId: Int) {
        val menuView = navView.get(0) as ViewGroup
        for (i in 0 until menuView.childCount) {
            val itemView = menuView.get(i)
            val isSelected = navView.menu.getItem(i).itemId == selectedId
            itemView.animate()
                .scaleX(if (isSelected) 1.2f else 1.0f)
                .scaleY(if (isSelected) 1.2f else 1.0f)
                .setDuration(200)
                .start()
        }
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