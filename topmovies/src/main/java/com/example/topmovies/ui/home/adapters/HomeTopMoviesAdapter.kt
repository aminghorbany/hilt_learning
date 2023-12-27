package com.example.topmovies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AsyncListUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.topmovies.databinding.ItemHomeMoviesTopBinding
import com.example.topmovies.models.home.ResponseMoviesList.Data
import javax.inject.Inject

class HomeTopMoviesAdapter @Inject constructor() : RecyclerView.Adapter<HomeTopMoviesAdapter.HomeViewHolder>() {

    private lateinit var binding: ItemHomeMoviesTopBinding
    val differ = AsyncListDiffer(this , object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    })

    inner class HomeViewHolder : ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun  bindData(item : Data){
            binding.apply {
                movieNameTxt.text = item.title.toString()
                movieInfoTxt.text = "${item.imdbRating } | ${item.country } | ${item.year } "
                moviePosterImg.load(item.poster){
                    crossfade(true)
                    crossfade(600)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemHomeMoviesTopBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return HomeViewHolder()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
        holder.setIsRecyclable(false) //for prevent duplicate item
    }

    override fun getItemCount() = 9

    override fun getItemViewType(position: Int) = position // for prevent confusing
}