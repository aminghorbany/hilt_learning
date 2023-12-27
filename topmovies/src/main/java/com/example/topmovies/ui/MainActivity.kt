package com.example.topmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.topmovies.R
import com.example.topmovies.databinding.ActivityMainBinding
import com.example.topmovies.utils.goneWidget
import com.example.topmovies.utils.showWidget
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            navController = findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)
            //show bottom navigation
            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.splashFragment || destination.id == R.id.registerFragment ){
                    goneWidget(bottomNav)
                }else{
                    showWidget(bottomNav)
                }
            }
        }

    }

    //handel onBackPress
    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}