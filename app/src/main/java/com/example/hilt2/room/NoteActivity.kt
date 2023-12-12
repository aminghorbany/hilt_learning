package com.example.hilt2.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Entity
import com.example.hilt2.R
import com.example.hilt2.databinding.ActivityMainBinding
import com.example.hilt2.databinding.ActivityNoteBinding
import com.example.hilt2.room.db.NotesModel
import com.example.hilt2.room.repository.DbRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding

    @Inject lateinit var notesAdapter: NotesAdapter
    @Inject lateinit var dbRepo: DbRepository
    @Inject lateinit var notesModel: NotesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            binding.btnSave.setOnClickListener {
                // save note
                notesModel.id = 0
                notesModel.title = titleEdt.text.toString()
                dbRepo.insertNote(notesModel)
                titleEdt.setText("")
                notesAdapter.differ.submitList(dbRepo.getAllNotes())
            }
            //get data
            notesAdapter.differ.submitList(dbRepo.getAllNotes())
            notesRV.apply {
                layoutManager = LinearLayoutManager(this@NoteActivity)
                adapter = notesAdapter
            }
            notesAdapter.setOnItemClickListener {
                Toast.makeText(this@NoteActivity, "${it.id} : ${it.title}", Toast.LENGTH_SHORT).show()
            }
        }



    }
}