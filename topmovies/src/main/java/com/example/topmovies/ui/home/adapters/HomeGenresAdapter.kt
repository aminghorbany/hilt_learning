package com.example.topmovies.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.topmovies.databinding.ItemHomeGenreListBinding
import com.example.topmovies.models.home.ResponseGenreList.ResponseGenreListItem
import javax.inject.Inject

class HomeGenresAdapter @Inject constructor() : RecyclerView.Adapter<HomeGenresAdapter.HomeViewHolder>() {

    private lateinit var binding: ItemHomeGenreListBinding
    val differ = AsyncListDiffer(this , object : DiffUtil.ItemCallback<ResponseGenreListItem>(){
        override fun areItemsTheSame(oldItem: ResponseGenreListItem, newItem: ResponseGenreListItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseGenreListItem, newItem: ResponseGenreListItem): Boolean {
            return oldItem == newItem
        }
    })

    inner class HomeViewHolder : ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun  bindData(item : ResponseGenreListItem){
            binding.apply {
                    nameTxt.text = item.name
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding = ItemHomeGenreListBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
                return HomeViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
        holder.setIsRecyclable(false) //for prevent duplicate item
    }

    override fun getItemViewType(position: Int) = position // for prevent confusing
}

