package com.example.s1_navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.s1_navigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            navController = findNavController(R.id.navHost)
            appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment , R.id.detailFragment))
            setupActionBarWithNavController(navController , appBarConfiguration)
        }

    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}