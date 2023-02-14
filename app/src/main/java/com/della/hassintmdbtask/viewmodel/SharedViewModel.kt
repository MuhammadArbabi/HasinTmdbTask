package com.della.hassintmdbtask.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {
    private var _selectedMovie : MutableState<Movie> = mutableStateOf(Movie())
    val selectedMovie :  MutableState<Movie> = _selectedMovie

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }
}