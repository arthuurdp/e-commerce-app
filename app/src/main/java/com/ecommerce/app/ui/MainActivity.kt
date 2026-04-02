package com.ecommerce.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ecommerce.app.R
import com.ecommerce.app.databinding.ActivityMainBinding
import com.ecommerce.app.util.hide
import com.ecommerce.app.util.show
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
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

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNav()
        observeSession()
    }

    private fun setupBottomNav() {
        val customerRootDestinations = setOf(
            R.id.homeFragment,
            R.id.searchFragment,
            R.id.profileFragment
        )

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Show bottom nav only for the main root destinations to avoid overlapping content
            if (destination.id in customerRootDestinations) {
                binding.bottomNavCustomer.show()
            } else {
                binding.bottomNavCustomer.hide()
            }

            updateBottomNavScale(binding.bottomNavCustomer, destination.id)
        }

        binding.bottomNavCustomer.setupWithNavController(navController)
    }

    private fun updateBottomNavScale(navView: BottomNavigationView, selectedId: Int) {
        val menuView = navView.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount) {
            val itemView = menuView.getChildAt(i) as BottomNavigationItemView
            val isSelected = itemView.id == selectedId
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