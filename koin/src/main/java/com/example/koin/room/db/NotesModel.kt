package com.example.koin.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.koin.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class MyNoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = ""
)
