package com.della.hassintmdbtask.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.util.toDp

private const val GRID_ROW_ITEMS = 3
private val GRID_SPACE = 4.dp

private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(GRID_ROW_ITEMS) }
@Composable
fun LazyGridMovie(
    state: LazyGridState,
    movies: LazyPagingItems<Movie>,
    modifier: Modifier = Modifier,
    onNavigationToMovieDetail : (Int)-> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier.background(Color.DarkGray),
        columns = GridCells.Fixed(GRID_ROW_ITEMS),
        contentPadding = PaddingValues(
            start = GRID_SPACE,
            end = GRID_SPACE,
            bottom = WindowInsets.navigationBars.getBottom(LocalDensity.current).toDp().dp.plus(GRID_SPACE)
        ),
        horizontalArrangement = Arrangement.spacedBy(GRID_SPACE, Alignment.CenterHorizontally),
        state = state,
        content = {

            if (movies.itemCount==0 && movies.loadState.refresh !is LoadState.Loading){
                item(span = span) {Text(
                    text = "No Movie...",
                    style = MaterialTheme.typography.subtitle1.copy(
                        color = Color.White,
                        letterSpacing = 1.5.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.W500
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                }
            }
            items(movies.itemCount,
            key= { index -> movies[index]!!.id  } ) { index ->

                val movie = movies.peek(index)?: return@items
                MovieItem(movie = movie,
                    modifier = Modifier
                    .height(280.dp)
                    .padding(vertical = GRID_SPACE),){ movieId->
                    onNavigationToMovieDetail(movieId)
                }
            }

        })
}