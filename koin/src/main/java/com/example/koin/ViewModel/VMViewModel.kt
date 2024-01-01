package com.example.koin.ViewModel

import androidx.lifecycle.ViewModel

class VMViewModel(private val repo : VMRepository) : ViewModel() {
    fun getName() = repo.userName()
}