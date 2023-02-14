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

/**
 *  The class uses the [Hilt] library to manage its dependencies and the constructor takes a [movieDetailRepository] parameter of type [MovieDetailRepository] which is injected using the [@Inject] annotation.

The class extends the ViewModel class from the Android Jetpack libraries. The MovieDetailViewModel class contains a private [_movieDetail] MutableState object that is used to store the movie detail. The movieDetail property is a public version of _movieDetail and is initially set to a value of Resource.Loading() in the constructor.

The class also contains a [getDetailOfMovie] function that takes a movieId parameter of type Int. The function launches a coroutine using the viewModelScope.launch method and updates the _movieDetail MutableState first to a value of Resource.Loading() and then to the result of calling the getMovieDetails function on the movieDetailRepository object with the given movieId.

This ViewModel class is responsible for retrieving the details of a specific movie from the repository and storing it as a Resource object in the MutableState _movieDetail object.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val movieDetailRepository: MovieDetailRepository) : ViewModel() {
    private var _movieDetail = mutableStateOf<Resource<MovieDetailResponse?>>(Resource.Loading())
    val movieDetail : MutableState<Resource<MovieDetailResponse?>> = _movieDetail

    suspend fun getDetailOfMovie(movieId:Int) = viewModelScope.launch {
        _movieDetail.value = Resource.Loading()
        _movieDetail.value = movieDetailRepository.getMovieDetails(movieId)
    }

}