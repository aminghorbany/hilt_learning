package com.example.hilt2.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hilt2.NOTE_TABLE

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(notesModel: NotesModel)

    @Query ("SELECT * FROM $NOTE_TABLE")
    fun getAllNotes() : MutableList<NotesModel>
}