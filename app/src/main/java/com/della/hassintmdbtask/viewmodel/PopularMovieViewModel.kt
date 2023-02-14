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

/**
 * The class uses the Hilt library to manage its dependencies, and the constructor takes a repository parameter of type [PopularMovieRepository] which is injected using the @Inject annotation.
 *
 * The class extends the ViewModel class from the Android Jetpack libraries. The PopularMovieViewModel class contains a private [_popularMovies] MutableState object that is used to store the flow of popular movies. The [popularMovies] property is a public version of [_popularMovies].
 *
 * In the init block, the [getPopularMovies] function is called, which updates the [_popularMovies] MutableState by calling the getPopularMovies function on the repository object. The returned flow of popular movies is cached using the cachedIn method with a viewModelScope argument. The flow is also caught using the catch function, which logs any errors with a call to Timber.d with the error message.
 *
 * This ViewModel class is responsible for retrieving the list of popular movies from the repository and storing it as a flow of PagingData objects in the MutableState [_popularMovies] object.
 */
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