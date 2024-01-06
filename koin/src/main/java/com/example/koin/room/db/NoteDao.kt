package com.example.koin.room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koin.NOTE_TABLE

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(noteModel: MyNoteModel)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNote(): LiveData<List<MyNoteModel>>
}