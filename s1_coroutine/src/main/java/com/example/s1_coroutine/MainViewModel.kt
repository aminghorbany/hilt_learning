package com.example.s1_coroutine

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val myName : MutableLiveData<String> by lazy { MutableLiveData<String>() }
}