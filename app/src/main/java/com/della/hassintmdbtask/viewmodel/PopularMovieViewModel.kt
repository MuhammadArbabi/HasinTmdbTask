package com.della.hassintmdbtask.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.domain.PopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val repository: PopularMovieRepository) :
    ViewModel() {
    private var _popularMovies : MutableState<Flow<PagingData<Movie>>> = mutableStateOf(flow {  })
    val popularMovies: MutableState<Flow<PagingData<Movie>>> = _popularMovies
    init {
        getPopularMovies()
    }
    fun getPopularMovies() {
        _popularMovies.value = repository.getPopularMovies().cachedIn(viewModelScope).catch { it ->
            it.message?.let { message ->
                Timber.d(message)
            }
        }
    }
}