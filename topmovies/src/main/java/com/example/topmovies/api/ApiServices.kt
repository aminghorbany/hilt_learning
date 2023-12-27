package com.example.topmovies.api

import com.example.topmovies.models.home.ResponseGenreList
import com.example.topmovies.models.home.ResponseMoviesList
import com.example.topmovies.models.register.BodyRegister
import com.example.topmovies.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body data : BodyRegister) : Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun getTopMoviesList(@Path("genre_id") genreId : Int , @Query("page") page : Int ) : Response<ResponseMoviesList>

    @GET("genres")
    suspend fun getGenreList() : Response<ResponseGenreList>
}