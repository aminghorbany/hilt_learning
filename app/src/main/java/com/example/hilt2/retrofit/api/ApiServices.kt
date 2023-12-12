package com.example.hilt2.retrofit.api

import com.example.hilt2.retrofit.model.ResponseMoviesList
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("/movies")
    fun getMovies() : Call<ResponseMoviesList>
}