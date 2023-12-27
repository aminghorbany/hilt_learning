package com.example.topmovies.repository

import com.example.topmovies.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api : ApiServices)  {

    suspend fun getTopMovies(genreId : Int , page : Int) = api.getTopMoviesList(genreId, page)

    suspend fun getGenreList() = api.getGenreList()

}