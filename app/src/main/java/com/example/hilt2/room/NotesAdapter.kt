package com.example.hilt2.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt2.databinding.ItemNotesBinding
import com.example.hilt2.room.db.NotesModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesAdapter @Inject constructor() : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private lateinit var binding: ItemNotesBinding
    val differ = AsyncListDiffer(this , object : DiffUtil.ItemCallback<NotesModel>(){
        override fun areItemsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
            return oldItem == newItem
        }
    })

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindData(data : NotesModel){
            binding.apply {
                titleTxt.text = "${data.id} -> ${data.title}"
                itemView.rootView.setOnClickListener {
                    sendDataVariable?.let {
                        it.invoke(data)
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        holder.bindData(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    private var sendDataVariable : ((NotesModel) -> Unit)? = null
    fun setOnItemClickListener(listener : (NotesModel) -> Unit){
        sendDataVariable = listener
    }

}