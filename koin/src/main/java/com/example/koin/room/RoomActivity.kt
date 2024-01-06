package com.example.koin.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koin.databinding.ActivityRoomBinding
import com.example.koin.room.db.MyNoteModel
import com.example.koin.room.viewmodel.RoomViewModel
import org.koin.android.ext.android.inject

class RoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding

    private val noteAdapter by lazy { NoteAdapter() }
    private val note by lazy { MyNoteModel() }

    private val vm : RoomViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*binding.apply {
            btnSave.setOnClickListener {
                val title = titleEdt.text.toString()
                note.id = 0
                note.title = title
                vm.saveNote(note)
                titleEdt.setText("")
            }
            //load notes
            vm.notesList.observe(this@RoomActivity){
                noteAdapter.differ.submitList(it)
                notesList.apply {
                    adapter = noteAdapter
                    layoutManager = LinearLayoutManager(this@RoomActivity)
                }

            }

        }*/
    }
}