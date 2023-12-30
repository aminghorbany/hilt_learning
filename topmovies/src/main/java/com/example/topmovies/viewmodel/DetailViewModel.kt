package com.example.topmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.models.detail.ResponseDetail
import com.example.topmovies.models.register.BodyRegister
import com.example.topmovies.models.register.ResponseRegister
import com.example.topmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo : DetailRepository):ViewModel(){

    val detailMovieLiveData = MutableLiveData<ResponseDetail>()
    val loading = MutableLiveData<Boolean>()

    fun loadDetailMovie(id : Int) = viewModelScope.launch {
        loading.postValue(true)
        val response = repo.getDetailMovie(id)
        if (response.isSuccessful){
            detailMovieLiveData.postValue(response.body())
        }
        loading.postValue(false)
    }
}