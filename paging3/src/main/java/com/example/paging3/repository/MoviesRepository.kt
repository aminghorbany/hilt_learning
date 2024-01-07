package com.example.paging3.repository

import com.example.paging3.api.ApiServices
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api : ApiServices) {

    suspend fun getAllMovies(page : Int) = api.getAllMovies(page)
}