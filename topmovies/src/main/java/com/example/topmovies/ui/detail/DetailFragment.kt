package com.example.topmovies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentDetailBinding
import com.example.topmovies.databinding.FragmentFavoriteBinding
import com.example.topmovies.utils.goneWidget
import com.example.topmovies.utils.initRecyclerView
import com.example.topmovies.utils.showShortToast
import com.example.topmovies.utils.showWidget
import com.example.topmovies.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel : DetailViewModel by viewModels()
    private val args : DetailFragmentArgs by navArgs()
    @Inject lateinit var imagesAdapter: ImagesAdapter
    private var movieId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = args.movieID
        //call api
        if (movieId > 0 ){
            viewModel.loadDetailMovie(movieId)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            //load data
            viewModel.detailMovieLiveData.observe(viewLifecycleOwner){
                posterBigImg.load(it.poster)
                posterNormalImg.load(it.poster){
                    crossfade(true)
                    crossfade(600)
                }
                movieNameTxt.text = it.title
                movieRateTxt.text = it.imdbRating
                movieTimeTxt.text = it.runtime
                movieDateTxt.text = it.released
                movieSummaryInfo.text = it.plot
                movieActorsInfo.text = it.actors
                //Image adapter
                imagesAdapter.differ.submitList(it.images)
                imagesRecyclerView.initRecyclerView(LinearLayoutManager(
                    requireContext() , RecyclerView.HORIZONTAL , false
                ) , imagesAdapter)
            }

            viewModel.loading.observe(viewLifecycleOwner){
                if (it){
                    requireContext().showWidget(detailLoading)
                    requireContext().goneWidget(detailScrollView)
                }else{
                    requireContext().showWidget(detailScrollView)
                    requireContext().goneWidget(detailLoading)
                }
            }

            onItemClick()

        }
    }

    private fun onItemClick() {
        binding.apply {
            favImg.setOnClickListener {
                requireContext().showShortToast("favImg")
            }
            backImg.setOnClickListener {
                requireContext().showShortToast("backImg")
            }
        }
    }
}