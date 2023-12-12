package com.example.hilt2.retrofit.repository

import com.example.hilt2.retrofit.api.ApiServices
import com.example.hilt2.retrofit.model.ResponseMoviesList
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Call
import javax.inject.Inject

@ActivityScoped // no problem if don't use
class ApiRepository @Inject constructor(private var api: ApiServices) {

    fun getAllMovies() = api.getMovies()
}