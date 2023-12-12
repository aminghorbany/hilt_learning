package com.example.hilt2.room.repository

import com.example.hilt2.room.db.NotesDao
import com.example.hilt2.room.db.NotesModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class DbRepository @Inject constructor(private val dao : NotesDao) {

    fun insertNote(notesModel: NotesModel) = dao.insertNote(notesModel)
    fun getAllNotes() = dao.getAllNotes()
}