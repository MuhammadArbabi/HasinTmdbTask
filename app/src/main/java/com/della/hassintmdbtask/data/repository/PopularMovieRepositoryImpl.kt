package com.della.hassintmdbtask.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.della.hassintmdbtask.data.api.ApiService
import com.della.hassintmdbtask.data.PopularMoviesPagingSource
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.domain.PopularMovieRepository
import kotlinx.coroutines.flow.Flow

class PopularMovieRepositoryImpl(private val apiService: ApiService) :PopularMovieRepository {
    override fun getPopularMovies(): Flow<PagingData<Movie>> = Pager(config = PagingConfig(pageSize = 20)) {
        PopularMoviesPagingSource(apiService = apiService)
    }.flow
}