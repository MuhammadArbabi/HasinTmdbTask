package com.della.hassintmdbtask.compose


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.della.hassintmdbtask.viewmodel.PopularMovieViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    modifier: Modifier = Modifier,
    popularMovieViewModel: PopularMovieViewModel = hiltViewModel(),
    onNavigationToMovieDetail: (Int) -> Unit
) {

    val moviesFlow = remember { popularMovieViewModel.getPopularMovies() }
    val movies = moviesFlow.collectAsLazyPagingItems()

    val state = rememberLazyGridState()
    Column() {


        TopAppBar(
            modifier = Modifier.background(
                Color.DarkGray
            ),
            actions = {
                IconButton(content = {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort",
                        tint = Color.White
                    )

                }, onClick = { })

            },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = Color.Transparent,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            ), title = {

                    Text(
                        text = "Popular",
                        color = Color.White
                    )

            }
        )
        when (movies.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                //TODO Error Item
                //state.error to get error message
            }
            is LoadState.Loading -> { // Loading UI
                //TODO show Loading
            }
            else -> {
                LazyGridMovie(state = state, movies = movies, modifier) { movieId ->
                    onNavigationToMovieDetail(movieId)
                }
            }
        }
    }
}
