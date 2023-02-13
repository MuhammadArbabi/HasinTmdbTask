package com.della.hassintmdbtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.domain.PopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val repository: PopularMovieRepository)
    : ViewModel() {
        fun getPopularMovies() : Flow<PagingData<Movie>> = repository.getPopularMovies().catch { it ->
            it.message?.let {message->
                Timber.d(message)
            }
        }
}