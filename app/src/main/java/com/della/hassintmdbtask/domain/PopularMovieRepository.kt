package com.della.hassintmdbtask.domain

import androidx.paging.PagingData
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import kotlinx.coroutines.flow.Flow

interface PopularMovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
}