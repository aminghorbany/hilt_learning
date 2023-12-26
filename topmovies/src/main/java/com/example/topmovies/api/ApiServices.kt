package com.example.topmovies.api

import com.example.topmovies.models.register.BodyRegister
import com.example.topmovies.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body data : BodyRegister) : Response<ResponseRegister>
}