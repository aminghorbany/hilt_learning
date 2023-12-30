package com.example.topmovies.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topmovies.R
import com.example.topmovies.databinding.FragmentFavoriteBinding
import com.example.topmovies.databinding.FragmentSearchBinding
import com.example.topmovies.ui.home.HomeFragmentDirections
import com.example.topmovies.utils.goneWidget
import com.example.topmovies.utils.initRecyclerView
import com.example.topmovies.utils.showWidget
import com.example.topmovies.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    @Inject
    lateinit var favoritesAdapter: FavoritesAdapter
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.getAllFavoriteListMovies() // its look like call api
            viewModel.allFavoriteListMoviesLiveData.observe(viewLifecycleOwner) {
                favoritesAdapter.setData(it)
                favoriteRecycler.initRecyclerView(LinearLayoutManager(requireContext()), favoritesAdapter)
            }
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    requireContext().showWidget(emptyItemsLay)
                    requireContext().goneWidget(favoriteRecycler)
                } else {
                    requireContext().showWidget(favoriteRecycler)
                    requireContext().goneWidget(emptyItemsLay)
                }
            }
            //onItemClick
            favoritesAdapter.onItemClickListener {
                val direction = FavoriteFragmentDirections.actionToDetailFragment(it.id) // FavoriteFragmentDirections origin
                findNavController().navigate(direction)
            }
        }
    }

}