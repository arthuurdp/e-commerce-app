package com.ecommerce.app.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    private fun setupKeyboardListener() {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            binding.root.getWindowVisibleDisplayFrame(r)
            val screenHeight = binding.root.rootView.height
            val keypadHeight = screenHeight - r.bottom

            // If keypad height is more than 15% of screen height, keyboard is likely visible
            val currentlyVisible = keypadHeight > screenHeight * 0.15
            if (currentlyVisible != isKeyboardVisible) {
                isKeyboardVisible = currentlyVisible
                handleBottomNavVisibility()
            }
        }
    }

    private fun handleBottomNavVisibility() {
        val customerRootDestinations = setOf(
            R.id.homeFragment,
            R.id.searchFragment,
            R.id.profileFragment
        )

        val isRootDestination = navController.currentDestination?.id in customerRootDestinations

        if (isRootDestination && !isKeyboardVisible) {
            binding.bottomNavCustomer.show()
        } else {
            binding.bottomNavCustomer.hide()
        }
    }

    private fun setupBottomNav() {
        navController.addOnDestinationChangedListener { _, _, _ ->
            handleBottomNavVisibility()
            navController.currentDestination?.let {
                updateBottomNavScale(binding.bottomNavCustomer, it.id)
            }
        }

        binding.bottomNavCustomer.setOnItemSelectedListener { item ->
            if (navController.currentDestination?.id == item.itemId) return@setOnItemSelectedListener false

            val options = androidx.navigation.NavOptions.Builder()
                .setPopUpTo(R.id.homeFragment, inclusive = false, saveState = true)
                .setLaunchSingleTop(true)
                .setRestoreState(true)
                .build()

            navController.navigate(item.itemId, null, options)
            true
        }
    }

    private fun updateBottomNavScale(navView: BottomNavigationView, selectedId: Int) {
        val menuView = navView.get(0) as ViewGroup
        for (i in 0 until menuView.childCount) {
            val itemView = menuView.get(i)
            val isSelected = navView.menu.getItem(i).itemId == selectedId
            val scale = if (isSelected) 1.2f else 1.0f
            itemView.animate()
                .scaleX(scale)
                .scaleY(scale)
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
