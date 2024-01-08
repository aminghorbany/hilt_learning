package com.example.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3.adapter.LoadMoreAdapter
import com.example.paging3.adapter.MoviesAdapter
import com.example.paging3.databinding.ActivityMainBinding
import com.example.paging3.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val vm : MoviesViewModel by viewModels()
    @Inject lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            //load data
            lifecycleScope.launch {
                //moviesList is Flow
                vm.moviesList.collect{
                    moviesAdapter.submitData(it)
                }
            }
            //loading => جدا بنویسیم روی هم تاثیر نمیزارن
            lifecycleScope.launch {
                moviesAdapter.loadStateFlow.collect{ // state of loading
                    val state = it.refresh
                    moviesLoading.isVisible = state is LoadState.Loading
                }
            }
            // recycler
            moviesRecycler.apply {
                adapter = moviesAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            // swipeRefresh
            movieSwipe.setOnRefreshListener {
                movieSwipe.isRefreshing = false
                moviesAdapter.refresh()
            }
            //load more
            moviesRecycler.adapter = moviesAdapter.withLoadStateFooter(
                LoadMoreAdapter{
                    moviesAdapter.retry()
                }
            )
        }
    }
}