package com.example.koin.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin.R
import com.example.koin.databinding.ActivityMainBinding
import com.example.koin.databinding.ActivityRetrofitBinding
import com.example.koin.retrofit.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRetrofitBinding
    private val moviesAdapter by  lazy { MoviesAdapter() }
    private val vm : MovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            //job
            vm.getMovies()

            vm.moviesListLD.observe(this@RetrofitActivity){
                moviesAdapter.differ.submitList(it.data)
                moviesRecycler.apply {
                    adapter = moviesAdapter
                    layoutManager = LinearLayoutManager(this@RetrofitActivity)
                }
            }
            vm.loading.observe(this@RetrofitActivity){
                if (it) {
                    moviesLoader.visibility = View.VISIBLE
                    moviesRecycler.visibility = View.GONE
                }else{
                    moviesRecycler.visibility = View.VISIBLE
                    moviesLoader.visibility = View.GONE
                }
            }
        }
    }
}