package com.example.koin.retrofit.di

import com.example.koin.ViewModel.vmModule
import com.example.koin.retrofit.repository.MovieRepository
import com.example.koin.retrofit.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {
    single { MovieRepository(get()) }
    viewModel { MovieViewModel(get()) }
}