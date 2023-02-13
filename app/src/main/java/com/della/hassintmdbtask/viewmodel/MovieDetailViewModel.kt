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
    private var _movieDetail = mutableStateOf<MovieDetailResponse?>(null)
    val movieDetail : MutableState<MovieDetailResponse?> = _movieDetail

    suspend fun getDetailOfMovie(movieId:Int) = viewModelScope.launch {
        movieDetailRepository.getMovieDetails(movieId).also { result->
           when(result){
               is Resource.Success ->{
                   _movieDetail.value = result.data
               }
               is Resource.Error ->{
                   Timber.d(result.statusMessage)
               }
               else -> {
                   // is Loading
               }
           }
        }
    }

}