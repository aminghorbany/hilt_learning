package com.example.hilt2.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt2.databinding.ActivityMovieBinding
import com.example.hilt2.goneWidget
import com.example.hilt2.retrofit.model.ResponseMoviesList
import com.example.hilt2.retrofit.repository.ApiRepository
import com.example.hilt2.showWidget
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    @Inject
    lateinit var apiRepo: ApiRepository
    @Inject
    lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            showWidget(movieLoading)
            apiRepo.getAllMovies().enqueue(object : Callback<ResponseMoviesList> {
                override fun onResponse(
                    call: Call<ResponseMoviesList>,
                    response: Response<ResponseMoviesList>
                ) {
                    goneWidget(movieLoading)
                        if (response.isSuccessful) {
                            response.body()?.let { itBody ->
                                itBody.data?.let {itData ->
                                    if (itData.isNotEmpty()){
                                        movieAdapter.differ.submitList(itData)
                                        Log.v("aska11" , itData.toString())
                                        moviesRV.apply {
                                            layoutManager = LinearLayoutManager(this@MovieActivity)
                                            adapter = movieAdapter
                                        }
                                    }
                                }
                            }
                        }
                    }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    goneWidget(movieLoading)
                    Log.v("aska" , t.message.toString())
                }

            })
        }

    }
}