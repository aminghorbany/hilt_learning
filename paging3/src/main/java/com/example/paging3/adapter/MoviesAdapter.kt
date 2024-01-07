package com.example.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.paging3.databinding.ItemMoviesBinding
import com.example.paging3.model.ResponseMovies.Data
import javax.inject.Inject

// strong power of paging is combine with diff utils - best performance
// PagingDataAdapter extended from RecyclerView.ViewHolder - first parameter is data that want to fill adapter second one is viewHolder
class MoviesAdapter @Inject constructor() : PagingDataAdapter<Data, MoviesAdapter.MoviesViewHolder>(differ) {

    private lateinit var binding: ItemMoviesBinding

    inner class MoviesViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Data) {
            binding.apply {
                itemMovieName.text = item.title
                itemMoviesImg.load(item.poster) {
                    crossfade(true)
                    crossfade(500)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder()
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindData(getItem(position)!!)  // getItem is for PagingDataAdapter
        holder.setIsRecyclable(false) // anti duplicate
    }

    companion object {
        // in companion object we can set differ in PagingDataAdapter constructor
        val differ = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

        }

    }

}