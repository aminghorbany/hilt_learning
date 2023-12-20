package com.example.hilt2.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hilt2.databinding.ActivityMainBinding
import com.example.hilt2.di.Family
import com.example.hilt2.di.FirstName
import com.example.hilt2.retrofit.MovieActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject @Family lateinit var userName : String
    @Inject @FirstName lateinit var familyName : String
    @Inject @Named("LastName") lateinit var lastName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            txtUserName.text = lastName
            txtResName.text = familyName
        }
        binding.btnPreviousPage.setOnClickListener {
            val intent = Intent(this , MovieActivity::class.java)
            startActivity(intent)
        }
    }
}