package com.example.topmovies.db

import androidx.room.*
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(moviesEntity: MoviesEntity)

    @Delete
    suspend fun deleteMovies(moviesEntity: MoviesEntity)

    @Query("select * from movies_table")
    suspend fun getAllMovies() : MutableList<MoviesEntity>  // we will use livedata in viewModel

    @Query("select exists (select 1 from movies_table where id = :movieId) ")
    suspend fun existsMovie(movieId : Int) : Boolean

}