package com.example.hilt2.retrofit.api

import android.graphics.pdf.PdfDocument.Page
import com.example.hilt2.retrofit.model.ResponseMoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movies")
    fun getMoviesList(@Query ("page") page: Int = 1) : Call<ResponseMoviesList>
}