package com.example.koin.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin.R
import com.example.koin.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val userInfo : UserInfo by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            txtName.text = userInfo.fullInfo()
        }
    }
}