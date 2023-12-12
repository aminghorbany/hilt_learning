package com.example.hilt2.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesModel::class] , version = 1 , exportSchema = false)
abstract class NotesDataBase : RoomDatabase(){
    abstract fun notesDao() : NotesDao


}