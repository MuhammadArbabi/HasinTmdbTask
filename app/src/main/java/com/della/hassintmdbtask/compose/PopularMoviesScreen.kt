package com.della.hassintmdbtask.compose


import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.della.hassintmdbtask.viewmodel.PopularMovieViewModel


@Composable
fun PopularMoviesScreen(
    modifier: Modifier = Modifier,
    popularMovieViewModel: PopularMovieViewModel = hiltViewModel<PopularMovieViewModel>()
) {

    val moviesFlow = remember{ popularMovieViewModel.getPopularMovies()}
    val movies = moviesFlow.collectAsLazyPagingItems()

    val state = rememberLazyGridState()


    when (movies.loadState.refresh) { //FIRST LOAD
        is LoadState.Error -> {
            //TODO Error Item
            //state.error to get error message
        }
        is LoadState.Loading -> { // Loading UI
            //TODO show Loading
        }
        else -> {
            LazyGridMovie(state = state, movies = movies, modifier)
        }
    }

}
