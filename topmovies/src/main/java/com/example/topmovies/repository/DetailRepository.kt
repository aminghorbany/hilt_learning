package com.example.topmovies.repository

import com.example.topmovies.api.ApiServices
import com.example.topmovies.db.MoviesDao
import com.example.topmovies.db.MoviesEntity
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api : ApiServices , private val dao : MoviesDao) {

    //Api
    suspend fun getDetailMovie(id : Int) = api.getMovieDetail(id)

    //DataBase
    suspend fun insertMovie(moviesEntity: MoviesEntity) = dao.insertMovies(moviesEntity)
    suspend fun deleteMovie(moviesEntity: MoviesEntity) = dao.deleteMovies(moviesEntity)
    suspend fun existsMovie(id: Int) = dao.existsMovie(id)
    // dao.getAllMovies() will use in favorite not here

}