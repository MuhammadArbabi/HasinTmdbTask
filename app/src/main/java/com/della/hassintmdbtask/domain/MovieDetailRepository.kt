package com.della.hassintmdbtask.domain

import androidx.paging.PagingData
import com.della.hassintmdbtask.data.model.movie.detail.MovieDetailResponse
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailResponse>
}