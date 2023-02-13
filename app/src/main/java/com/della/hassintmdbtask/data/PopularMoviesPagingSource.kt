package com.della.hassintmdbtask.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.della.hassintmdbtask.data.api.ApiService
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import retrofit2.HttpException
import java.io.IOException

class PopularMoviesPagingSource(private val apiService: ApiService) : PagingSource<Int,Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getPopularMovies(page = page)

            LoadResult.Page(
                data = response.movies,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.movies.isEmpty()) null else page.plus(1),
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}