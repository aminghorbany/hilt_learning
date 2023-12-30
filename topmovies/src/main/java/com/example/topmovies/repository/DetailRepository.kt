package com.example.topmovies.repository

import com.example.topmovies.api.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api : ApiServices) {

    suspend fun getDetailMovie(id : Int) = api.getMovieDetail(id)

}