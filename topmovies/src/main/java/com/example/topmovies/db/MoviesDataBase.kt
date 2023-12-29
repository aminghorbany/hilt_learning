package com.example.topmovies.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MoviesEntity::class] , version = 1 , exportSchema = false)
abstract class MoviesDataBase : RoomDatabase(){
    abstract fun moviesDao() : MoviesDao
}