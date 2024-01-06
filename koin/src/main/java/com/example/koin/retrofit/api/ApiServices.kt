package com.example.koin.retrofit.api

import com.example.koin.retrofit.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("movies")
    suspend fun allMovies(): Response<ResponseMovies>
}