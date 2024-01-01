package com.example.koin.qualifiers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin.databinding.ActivityQualifiersBinding
import org.koin.android.ext.android.inject

class QualifiersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQualifiersBinding
    private val human : Human by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQualifiersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            txtInfo.text = human.getInfo()
        }
    }
}