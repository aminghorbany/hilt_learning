package com.example.paging3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3.databinding.LoadMoreBinding

// we didn't need diffUtils
class LoadMoreAdapter(private val retry : () -> Unit) : LoadStateAdapter<LoadMoreAdapter.LoadMoreViewHolder>() {


    private lateinit var binding: LoadMoreBinding
    inner class LoadMoreViewHolder(retry : () -> Unit) :  RecyclerView.ViewHolder(binding.root){

        init {
            // بلافاصله اجرا می شه و کلیک کنیم رو دکمه عمل انجام میشه
            binding.loadMoreRetry.setOnClickListener { retry.invoke() }
        }

        fun bindData(state : LoadState){
            binding.apply {
                //handle widgets state
                if (state is LoadState.Loading){
                    loadMoreProgress.isVisible = true
                    loadMoreTxtError.isVisible = false
                    loadMoreRetry.isVisible = false
                }
                if (state is LoadState.Error){
                    loadMoreProgress.isVisible = false
                    loadMoreTxtError.isVisible = true
                    loadMoreRetry.isVisible = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadMoreAdapter.LoadMoreViewHolder {
        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return LoadMoreViewHolder(retry)
    }

    override fun onBindViewHolder(holder: LoadMoreAdapter.LoadMoreViewHolder, loadState: LoadState) {
        holder.bindData(loadState)
//        holder.setIsRecyclable(false)  //for prevent duplicate item
    }
    //PagingDataAdapter have Retry method so can use it
    //we should handle the retry button with lambda fun

}