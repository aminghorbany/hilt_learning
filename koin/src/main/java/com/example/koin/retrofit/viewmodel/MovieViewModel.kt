package com.example.koin.retrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin.retrofit.model.ResponseMovies
import com.example.koin.retrofit.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repo : MovieRepository) : ViewModel() {

    val moviesListLD = MutableLiveData<ResponseMovies>()
    val loading = MutableLiveData<Boolean>()

    fun getMovies() = viewModelScope.launch {
        loading.postValue(true)

        val response = repo.getAllMovies()
        if (response.isSuccessful) {
            moviesListLD.postValue(response.body())
        }
        loading.postValue(false)
    }
}