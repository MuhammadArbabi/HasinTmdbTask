package com.della.hassintmdbtask.data.repository

import com.della.hassintmdbtask.data.api.ApiService
import com.della.hassintmdbtask.data.model.movie.detail.MovieDetailResponse
import com.della.hassintmdbtask.domain.MovieDetailRepository
import com.della.hassintmdbtask.util.Resource

class MovieDetailRepositoryImpl(private val apiService: ApiService) : MovieDetailRepository {
    override suspend fun getMovieDetails(movieId:Int): Resource<MovieDetailResponse?> {
        val apiResponse = try {
            apiService.getMovieDetails(movieId)
        }catch (e : java.lang.Exception){
            return Resource.Error(e.toString())
        }
        return Resource.Success(apiResponse)
    }
}