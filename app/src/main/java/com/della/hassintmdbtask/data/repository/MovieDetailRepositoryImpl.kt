package com.della.hassintmdbtask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.della.hassintmdbtask.data.api.ApiService
import com.della.hassintmdbtask.data.model.movie.PopularMoviesPagingSource
import com.della.hassintmdbtask.data.model.movie.detail.MovieDetailResponse
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.domain.MovieDetailRepository
import com.della.hassintmdbtask.domain.PopularMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailRepositoryImpl(private val apiService: ApiService) : MovieDetailRepository {
    override suspend fun getMovieDetails(movieId:Int): MovieDetailResponse = apiService.getMovieDetails(movieId)
}