package com.example.koin.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.koin.R
import com.example.koin.databinding.ActivityViewModelBinding
import org.koin.android.ext.android.inject

class ViewModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewModelBinding
    private val viewModel : VMViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            txtFullName.text = viewModel.getName()
        }
    }
}