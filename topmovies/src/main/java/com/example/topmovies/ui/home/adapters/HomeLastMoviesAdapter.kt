package com.example.topmovies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.topmovies.databinding.ItemHomeMoviesLastBinding
import com.example.topmovies.databinding.ItemHomeMoviesTopBinding
import com.example.topmovies.models.home.ResponseMoviesList.Data
import javax.inject.Inject

class HomeLastMoviesAdapter @Inject constructor() : RecyclerView.Adapter<HomeLastMoviesAdapter.HomeViewHolder>() {

    private lateinit var binding: ItemHomeMoviesLastBinding

    // 1. define empty list and pass it to adapter
    private var moviesList = emptyList<Data>()

    // 2. define DiffUtilsClass - if we need this class for another adapter we define it out of this adapter
    class MoviesDiffUtils(private val oldItems : List<Data> , private val newItems : List<Data>) : DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] === newItems[newItemPosition] // three equal check value and dataType
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] === newItems[newItemPosition] // when call ==> areItemsTheSame is true
        }

    }

    // 3. using MoviesDiffUtils in fun
    fun setData(data: List<Data>){
        val moviesDiffUtils = MoviesDiffUtils(moviesList , data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtils) // calculate Different
        moviesList = data // update old items with new items
        diffUtils.dispatchUpdatesTo(this) // update in which adapter? this adapter
    }

    inner class HomeViewHolder : ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun  bindData(item : Data){
            binding.apply {
                movieNameTxt.text = item.title.toString()
                movieRateTxt.text = item.imdbRating.toString()
                movieCountryTxt.text = item.country.toString()
                movieYearTxt.text = item.year.toString()
                moviePosterImg.load(item.poster){
                    crossfade(true)
                    crossfade(600)
                }
                root.setOnClickListener {
                    onItemClick?.invoke(item)
                }
            }
        }
    }

    private var onItemClick : ((Data) -> Unit?) ?= null
    fun onItemClickListener(listener : (Data) -> Unit? ){
        onItemClick = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemHomeMoviesLastBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return HomeViewHolder()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(moviesList[position])
        holder.setIsRecyclable(false) //for prevent duplicate item
    }

    override fun getItemCount() = moviesList.size

    override fun getItemViewType(position: Int) = position // for prevent confusing
}