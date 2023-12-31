package com.example.topmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.db.MoviesEntity
import com.example.topmovies.models.detail.ResponseDetail
import com.example.topmovies.models.register.BodyRegister
import com.example.topmovies.models.register.ResponseRegister
import com.example.topmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repo : DetailRepository):ViewModel(){

    //Api
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

    //DataBase
    //viewModelScope.launch will not return something , await() in async will return something  ,
    //when we don't need await() in async immediately so we can use withContext (also can use it changing thread)
    val isFavorite =  MutableLiveData<Boolean>()
    // with ctrl + q we will see the data type
    suspend fun existsMovie(id : Int) = withContext(viewModelScope.coroutineContext){
        repo.existsMovie(id) // so in this case it will return boolean but in launch case it will do something(Job)
    }

    // we wanna do some work , we don't want return something
    fun favoriteMovie(id: Int , entity: MoviesEntity) = viewModelScope.launch {
        val exists = repo.existsMovie(id)
        if (exists){
            isFavorite.postValue(false)
            repo.deleteMovie(entity) // if exist in db should delete and remove from favorite
        }else{
            isFavorite.postValue(true)
            repo.insertMovie(entity)
        }
    }
}