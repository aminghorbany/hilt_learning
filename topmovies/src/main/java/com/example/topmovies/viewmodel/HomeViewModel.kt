package com.example.topmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.models.home.ResponseGenreList
import com.example.topmovies.models.home.ResponseMoviesList
import com.example.topmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo : HomeRepository) : ViewModel() {

    // we have 3 api but 1 loading after api we will handle loading - for better name set LiveData at the end of variables.
    val getTopMoviesListLiveData = MutableLiveData<ResponseMoviesList>()
    val getGenreListLiveData = MutableLiveData<ResponseGenreList>()

    fun getTopMoviesList(genreId : Int , page : Int) = viewModelScope.launch {
        val response = repo.getTopMovies(genreId , page)
        if (response.isSuccessful){
            getTopMoviesListLiveData.postValue(response.body())
        }
    }

    fun getGenreList() = viewModelScope.launch {
        val response = repo.getGenreList()
        if (response.isSuccessful){
            getGenreListLiveData.postValue(response.body())
        }
    }

}