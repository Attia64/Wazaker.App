package com.attia.wazaker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.attia.wazaker.R
import com.attia.wazaker.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }


    private fun setupBottomNavigation() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.homeNavHost) as NavHostFragment
        binding.bottomNav.setupWithNavController(navController.navController)

        navController.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.specificAzkaarFragment || destination.id == R.id.myAzkaarFragment) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }

        }
    }
}