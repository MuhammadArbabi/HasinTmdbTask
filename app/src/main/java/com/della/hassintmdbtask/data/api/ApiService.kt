package com.della.hassintmdbtask.data.api

import com.della.hassintmdbtask.BuildConfig
import com.della.hassintmdbtask.data.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(value = "page") page: Int,
        @Query(value = "api_key") apiKey: String = BuildConfig.API_KEY
    ): PopularMoviesResponse


}