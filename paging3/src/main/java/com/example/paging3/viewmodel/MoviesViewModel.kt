package com.example.paging3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paging3.paging.MoviesPagingSource
import com.example.paging3.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo : MoviesRepository) : ViewModel() {

    //pageSize = 1 because we don't know how much page we have
    val moviesList = Pager(PagingConfig(1)){
        MoviesPagingSource(repo)
    }.flow.cachedIn(viewModelScope) // flow for streaming data - no need mutableLiveData - has different loading - need adapter
}