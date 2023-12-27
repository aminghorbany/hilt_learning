package com.example.topmovies.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentHomeBinding
import com.example.topmovies.models.home.ResponseMoviesList
import com.example.topmovies.ui.home.adapters.HomeTopMoviesAdapter
import com.example.topmovies.utils.initRecyclerView
import com.example.topmovies.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    @Inject lateinit var homeTopMoviesAdapter: HomeTopMoviesAdapter
    private val homeViewModel : HomeViewModel by viewModels()
    private val pageHelper : PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // our api will call only one time - but if we call in onViewCreated it will call every time
        // call api
        homeViewModel.getTopMoviesList(3, 1)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycle.coroutineScope.launchWhenCreated {
                homeViewModel.getTopMoviesListLiveData.observe(viewLifecycleOwner){
                    homeTopMoviesAdapter.differ.submitList(it.data)
                    topMoviesRecycler.initRecyclerView(LinearLayoutManager(requireContext() ,
                        LinearLayoutManager.HORIZONTAL , false  ) , homeTopMoviesAdapter) // reverse is good for persian app
                    // attach pageHelper to RecyclerView
                    pageHelper.attachToRecyclerView(topMoviesRecycler)
                    // init indicator
                    topMoviesIndicator.attachToRecyclerView(topMoviesRecycler , pageHelper )
                }
            }
        }
    }
}