package com.example.hilt2.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hilt2.NOTE_TABLE

@Entity(tableName = NOTE_TABLE)
data class NotesModel(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0 ,
    var title : String = ""
)