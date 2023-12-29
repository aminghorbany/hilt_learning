package com.example.topmovies.repository

import com.example.topmovies.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api : ApiServices) {

    suspend fun searchMoviesList(name : String) = api.searchMoviesList(name)

}