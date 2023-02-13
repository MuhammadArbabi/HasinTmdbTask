package com.della.hassintmdbtask.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.della.hassintmdbtask.data.model.movie.detail.MovieDetailResponse
import com.della.hassintmdbtask.domain.MovieDetailRepository
import com.della.hassintmdbtask.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieDetailRepository: MovieDetailRepository) : ViewModel() {
    private var _movieDetail = mutableStateOf<Resource<MovieDetailResponse?>>(Resource.Loading())
    val movieDetail : MutableState<Resource<MovieDetailResponse?>> = _movieDetail

    suspend fun getDetailOfMovie(movieId:Int) = viewModelScope.launch {
        _movieDetail.value = Resource.Loading()
        _movieDetail.value = movieDetailRepository.getMovieDetails(movieId)
    }

}