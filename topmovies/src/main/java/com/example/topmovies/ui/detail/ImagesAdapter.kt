package com.example.topmovies.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.topmovies.databinding.ItemDetailImagesBinding
import com.example.topmovies.databinding.ItemHomeGenreListBinding
import com.example.topmovies.models.detail.ResponseDetail
import com.example.topmovies.models.home.ResponseGenreList.ResponseGenreListItem
import javax.inject.Inject

class ImagesAdapter @Inject constructor() : RecyclerView.Adapter<ImagesAdapter.HomeViewHolder>() {

    private lateinit var binding: ItemDetailImagesBinding
    val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    })

    inner class HomeViewHolder : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(item: String) {
            binding.apply {
                itemImages.load(item){
                    crossfade(true)
                    crossfade(600)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemDetailImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
        holder.setIsRecyclable(false) //for prevent duplicate item
    }

    override fun getItemViewType(position: Int) = position // for prevent confusing
}

