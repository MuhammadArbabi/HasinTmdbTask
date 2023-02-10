package com.della.hassintmdbtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.domain.PopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val repository: PopularMovieRepository)
    : ViewModel() {
        fun getPopularMovies() : Flow<PagingData<Movie>> = repository.getPopularMovies()
}