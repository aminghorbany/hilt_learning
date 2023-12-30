package com.example.topmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.db.MoviesEntity
import com.example.topmovies.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repo : FavoriteRepository) : ViewModel() {

    val allFavoriteListMoviesLiveData = MutableLiveData<MutableList<MoviesEntity>>()
    val empty = MutableLiveData<Boolean>()

    fun getAllFavoriteListMovies() = viewModelScope.launch {
        val data = repo.getAllFavoriteList()
        if (data.isEmpty()){
            empty.postValue(true)
        }else{
            empty.postValue(false)
            allFavoriteListMoviesLiveData.postValue(data)
        }
    }
}