package com.example.koin.retrofit.repository

import com.example.koin.retrofit.api.ApiServices

class MovieRepository(private val api : ApiServices) {
    suspend fun getAllMovies() = api.allMovies()
}