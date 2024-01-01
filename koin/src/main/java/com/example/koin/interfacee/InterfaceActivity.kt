package com.example.koin.interfacee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin.databinding.ActivityInterfaceBinding
import org.koin.android.ext.android.inject

class InterfaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInterfaceBinding
    private val carInfo : CarInfo by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterfaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            txtModel.text = carInfo.getCarInfo()
        }
    }
}