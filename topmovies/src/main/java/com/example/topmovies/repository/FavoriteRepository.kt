package com.example.topmovies.repository

import com.example.topmovies.db.MoviesDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao : MoviesDao) {

    suspend fun getAllFavoriteList() = dao.getAllMovies()
}