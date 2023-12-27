package com.example.topmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.topmovies.models.register.BodyRegister
import com.example.topmovies.models.register.ResponseRegister
import com.example.topmovies.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repo : RegisterRepository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val responseRegisterData = MutableLiveData<ResponseRegister>()
    fun doRegisterUser(data : BodyRegister) = viewModelScope.launch {
        loading.postValue(true)
        val response = repo.registerUser(data)
        if (response.isSuccessful){
            responseRegisterData.postValue(response.body())
        }
        loading.postValue(false)
    }
}