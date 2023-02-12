package com.della.hassintmdbtask.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.della.hassintmdbtask.R
import com.della.hassintmdbtask.data.model.movie.detail.MovieDetailResponse
import com.della.hassintmdbtask.util.toBackdropUrl
import com.della.hassintmdbtask.util.toPosterUrl
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment

private val headerHeight = 250.dp
private val toolbarHeight = 56.dp

@Composable
fun MovieInfoScreen(movie: MovieDetailResponse, onBackClick: () -> Unit) {
    val scroll: ScrollState = rememberScrollState(0)
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }



    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(
            backdropPath = movie.backdropPath,
            movieTitle = movie.title,
            scroll = scroll,
            headerHeightPx = headerHeightPx
        )

        Body(movie, scroll)

        Toolbar(
            movie.title,
            scroll,
            headerHeightPx,
            toolbarHeightPx,
            onBack = onBackClick,
            {/*TODO*/ },
            {/*TODO*/ })


    }


}

@Composable
private fun Header(
    scroll: ScrollState,
    headerHeightPx: Float,
    backdropPath: String,
    movieTitle: String
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(headerHeight)
        .graphicsLayer {
            translationY = -scroll.value.toFloat() * 1.4F // Parallax effect
            alpha = (-1f / headerHeightPx) * scroll.value + 1
        }
    ) {
        val painter = rememberAsyncImagePainter(
            model = backdropPath.toBackdropUrl(),
            error = rememberVectorPainter(Icons.Filled.BrokenImage),
            placeholder = rememberVectorPainter(Icons.Default.Movie)
        )
        val scale =
            if (painter.state !is AsyncImagePainter.State.Success) ContentScale.Fit else ContentScale.FillBounds

        Image(
            painter = painter,
            contentDescription = stringResource(
                id = R.string.poster_content_description,
                movieTitle
            ),
            contentScale = scale,
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    movieTitle: String,
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    onBack: () -> Unit = {},
    onShare: () -> Unit = {},
    onFavorite: () -> Unit = {}
) {
    val toolbarBottom = headerHeightPx - (2 * toolbarHeightPx)
    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }
    val transition = updateTransition(targetState = showToolbar, label = "")
    val color by transition.animateColor(label = "") { visible ->
        if (visible)
            Color.DarkGray
        else
            Color.Transparent
    }

    TopAppBar(
        modifier = Modifier.background(
            color
        ),
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "null",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(content = {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White
                )

            }, onClick = onShare)
            IconButton(content = {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = Color.White
                )

            }, onClick = onFavorite)
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ), title = {
            AnimatedVisibility(
                visible = showToolbar,
                enter = fadeIn(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300))
            ) {
                Text(text = movieTitle,
                color = Color.White)
            }
        }
    )

}

@Composable
private fun Body(movie: MovieDetailResponse, scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight - toolbarHeight))

        BriefInfo(movie = movie)
        RateRow(movie)
        Overview(movie)

    }
}

@Composable
private fun BriefInfo(movie: MovieDetailResponse) {
    Row() {
        ElevatedCard(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            shape = CardDefaults.elevatedShape
        ) {
            AsyncImage(
                model = movie.posterPath.toPosterUrl(),
                contentDescription = movie.title
            )
        }
        Column(modifier = Modifier.padding(top = toolbarHeight)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )

            FlowRow(
                mainAxisSpacing = 4.dp,
                crossAxisSpacing = 4.dp,
                mainAxisAlignment = MainAxisAlignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)

            ) {
                movie.genres.forEach {
                    ChipsItem(name = it.name)
                }
            }
        }
    }
}


@Composable
fun RateRow(movie: MovieDetailResponse) {
    Column() {
        Divider(color = Color.Gray, thickness = 1.dp)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = movie.voteAverage.toString())
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "null"
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        movie.voteCount.toString(),
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        "Votes",
                        color = Color.Gray
                    )
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = "null"
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = movie.originalLanguage)
                }
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Language",
                    color = Color.Gray
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.WatchLater,
                        contentDescription = "null"
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = movie.releaseDate)
                }
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Release date",
                    color = Color.Gray
                )
            }
        }
        Divider(color = Color.Gray, thickness = 1.dp)
    }
}

@Composable
fun Overview(movie: MovieDetailResponse) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = stringResource(R.string.overview))
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(500.dp))
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChipsItem(name: String) {
    Chip(
        onClick = {},
        enabled = false,
        border = BorderStroke(
            ChipDefaults.OutlinedBorderSize,
            Color.Gray
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = Color.White,
            contentColor = Color.Gray
        ),
    ) {
        Text(text = name)
    }
}