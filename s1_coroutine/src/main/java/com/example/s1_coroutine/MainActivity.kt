package com.example.s1_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.s1_coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var job: Job

    private var counter = 0
    private val mainViewModel : MainViewModel by viewModels()
    private val checkConnection by lazy { CheckConnection(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*job = CoroutineScope(Dispatchers.IO).launch {
            repeat(3){
                doWork1()
            }
            withTimeoutOrNull(3000){
                for (i in 1..5){
                    Log.v("asawq1" , "aaa")
                    delay(1000)
                }
            }
        }*/


        binding.apply {
            btn.setOnClickListener {
                mainViewModel.myName.postValue("amin ghorbany")
            }
        }

        mainViewModel.myName.observe(this ){
            binding.txtCounter.text = mainViewModel.myName.value
        }

        checkConnection.observe(this){
            if (it){
                // yes
                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show()
            }else{
                // no
                Toast.makeText(this, "noo", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
