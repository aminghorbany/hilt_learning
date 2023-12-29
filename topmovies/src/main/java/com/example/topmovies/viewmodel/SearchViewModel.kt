package com.example.topmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.models.home.ResponseMoviesList
import com.example.topmovies.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repo : SearchRepository) : ViewModel() {

    val getSearchMoviesListLiveData = MutableLiveData<ResponseMoviesList>()
    val searchLoading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    fun getSearchMoviesList(name : String) = viewModelScope.launch {
        searchLoading.postValue(true)
        val response = repo.searchMoviesList(name)
        if (response.isSuccessful){
            if (response.body()?.data!!.isNotEmpty()){
                empty.postValue(false)
                getSearchMoviesListLiveData.postValue(response.body())
            }else{
                empty.postValue(true)
            }
        }
        searchLoading.postValue(false)
    }
}