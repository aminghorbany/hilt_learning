package com.example.topmovies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentDetailBinding
import com.example.topmovies.databinding.FragmentFavoriteBinding
import com.example.topmovies.db.MoviesEntity
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
    @Inject lateinit var entity: MoviesEntity
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
            viewModel.detailMovieLiveData.observe(viewLifecycleOwner){ res ->
                posterBigImg.load(res.poster)
                posterNormalImg.load(res.poster){
                    crossfade(true)
                    crossfade(600)
                }
                movieNameTxt.text = res.title
                movieRateTxt.text = res.imdbRating
                movieTimeTxt.text = res.runtime
                movieDateTxt.text = res.released
                movieSummaryInfo.text = res.plot
                movieActorsInfo.text = res.actors
                //Image adapter
                imagesAdapter.differ.submitList(res.images)
                imagesRecyclerView.initRecyclerView(LinearLayoutManager(
                    requireContext() , RecyclerView.HORIZONTAL , false
                ) , imagesAdapter)

                favImg.setOnClickListener {
                    entity.id = movieId
                    entity.poster = res.poster.toString()
                    entity.title = res.title.toString()
                    entity.year = res.year.toString()
                    entity.country = res.country.toString()
                    entity.rate = res.imdbRating.toString()
                    viewModel.favoriteMovie(movieId , entity)
                }
            }
            //loading
            viewModel.loading.observe(viewLifecycleOwner){
                if (it){
                    requireContext().showWidget(detailLoading)
                    requireContext().goneWidget(detailScrollView)
                }else{
                    requireContext().showWidget(detailScrollView)
                    requireContext().goneWidget(detailLoading)
                }
            }
            //default fav color
            lifecycleScope.launchWhenCreated {
                if (viewModel.existsMovie(movieId)){
                    favImg.setColorFilter(ContextCompat.getColor(requireContext() , R.color.scarlet))
                }else{
                    favImg.setColorFilter(ContextCompat.getColor(requireContext() , R.color.philippineSilver))
                }
            }
            //change image with click
            viewModel.isFavorite.observe(viewLifecycleOwner){
                if (it){
                    favImg.setColorFilter(ContextCompat.getColor(requireContext() , R.color.scarlet))
                }else{
                    favImg.setColorFilter(ContextCompat.getColor(requireContext() , R.color.philippineSilver))
                }
            }

            //back button
            backImg.setOnClickListener {
                findNavController().navigateUp()
//                findNavController().popBackStack() //can use both of them.
            }
        }
    }
}