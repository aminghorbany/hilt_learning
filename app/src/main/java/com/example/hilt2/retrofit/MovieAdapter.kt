package com.example.hilt2.retrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hilt2.databinding.ItemMoviesBinding
import com.example.hilt2.databinding.ItemNotesBinding
import com.example.hilt2.retrofit.model.ResponseMoviesList
import com.example.hilt2.room.db.NotesModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieAdapter @Inject constructor(@ApplicationContext context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var binding: ItemMoviesBinding
    val differ = AsyncListDiffer(this , object : DiffUtil.ItemCallback<ResponseMoviesList.Data>(){
        override fun areItemsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResponseMoviesList.Data, newItem: ResponseMoviesList.Data): Boolean {
            return oldItem == newItem
        }
    })

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindData(data : ResponseMoviesList.Data){
            binding.apply {
                movieName.text = data.title
                movieImg.load(data.poster){
                    crossfade(true)
                    crossfade(800)
                }
            }
        }

    }

    // برای جلوگری از نشان دادن آیتم تکراری
    // داپلیکیت ایجاد میشه مثلا آیتم اول میادش توی آیتم آخر تکرار میشه
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    /*private var sendDataVariable : ((NotesModel) -> Unit)? = null
    fun setOnItemClickListener(listener : (NotesModel) -> Unit){
        sendDataVariable = listener
    }*/

}