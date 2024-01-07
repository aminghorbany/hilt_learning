package com.example.paging3.api

import com.example.paging3.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movies")
    suspend fun getAllMovies(@Query("page") page : Int) : Response<ResponseMovies>
}