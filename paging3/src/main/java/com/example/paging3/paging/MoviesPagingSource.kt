package com.example.paging3.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.model.ResponseMovies
import com.example.paging3.repository.MoviesRepository
import javax.inject.Inject

class MoviesPagingSource @Inject constructor(private val repo : MoviesRepository) : PagingSource
    // we send page(Int) we receive response (our dataclass data)
    <Int , ResponseMovies.Data>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseMovies.Data>): Int? {
        //we don't need it so return null
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseMovies.Data> {
        // we must use try catch
        return try {
            val currentPage = params.key ?: 1 //if it was null get 1 , because page start from 1
            //get data
            val currentResponse = repo.getAllMovies(currentPage)
            val currentData = currentResponse.body()?.data ?: emptyList()

            // submitList in differ do addAll in mutableList so we simulate it here
            val myData = mutableListOf<ResponseMovies.Data>()
            myData.addAll(currentData)

            LoadResult.Page(
                data = myData ,
                prevKey = if (currentPage == 1) null else -1 ,
                nextKey = currentPage.plus(1)
                )
        }catch (err : Exception){
            LoadResult.Error(err)
        }
    }
    // now we will use this in viewModel
}