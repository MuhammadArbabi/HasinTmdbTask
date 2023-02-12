package com.della.hassintmdbtask.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.della.hassintmdbtask.data.model.movie.detail.MovieDetailResponse
import com.della.hassintmdbtask.domain.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieDetailRepository: MovieDetailRepository) : ViewModel() {
    private var _movieDetail = mutableStateOf<MovieDetailResponse?>(null)
    val movieDetail : MutableState<MovieDetailResponse?> = _movieDetail

    suspend fun getDetailOfMovie(movieId:Int) = viewModelScope.launch {
        movieDetailRepository.getMovieDetails(movieId).also {
            _movieDetail.value  = it
        }
    }

}