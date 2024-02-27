package com.attia.wazaker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.attia.wazaker.R
import com.attia.wazaker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeNavHost) as NavHostFragment
        binding.bottomNav.setupWithNavController(navHostFragment.navController)
    }

/*
    private fun controlBottomNav() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.myAzkaarFragment) {
                binding.bottomNav.visibility = View.GONE
            }
            else {
                binding.bottomNav.visibility = View.VISIBLE
            }

        }
    }
 */
}