package com.della.hassintmdbtask.data.api

import com.della.hassintmdbtask.data.model.movie.detail.MovieDetailResponse
import com.della.hassintmdbtask.data.model.movie.popular.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query(value = "page") page: Int): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path(value = "movie_id") movieId: Int) : MovieDetailResponse
}