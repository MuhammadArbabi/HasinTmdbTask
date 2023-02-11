package com.della.hassintmdbtask.compose

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.della.hassintmdbtask.R
import com.della.hassintmdbtask.data.model.movie.popular.Movie
import com.della.hassintmdbtask.ui.theme.HassinTmdbTaskTheme
import com.della.hassintmdbtask.util.toPosterUrl

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onMovieClicked: (Int) -> Unit = {}
) {
    Box(modifier = modifier) {
        Card(modifier = Modifier
            .fillMaxSize(),
            shape = RoundedCornerShape(size = 4.dp),
            backgroundColor = Color.Gray,
            onClick = { onMovieClicked(movie.id) }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MoviePoster(posterPath = movie.posterPath, movieName = movie.title)
                Spacer(modifier = Modifier.height(8.dp))
                MovieName(name = movie.title)
            }
        }
    }
}

@Composable
private fun MoviePoster(posterPath: String, movieName: String) {
    val painter = rememberAsyncImagePainter(
        model = posterPath.toPosterUrl(),
        error = rememberVectorPainter(Icons.Filled.BrokenImage),
        placeholder = rememberVectorPainter(Icons.Default.Movie)
    )
    val scale =
        if (painter.state !is AsyncImagePainter.State.Success) ContentScale.Fit else ContentScale.FillBounds

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.poster_content_description, movieName),
        contentScale = scale,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85F)
    )
}

@Composable
private fun MovieName(name: String) = Text(
    text = name,
    modifier = Modifier.fillMaxHeight(),
    style = MaterialTheme.typography.subtitle1.copy(
        color = Color.White,
        letterSpacing = 1.5.sp,
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.W500
    ),
    maxLines = 2,
    overflow = TextOverflow.Ellipsis
)

@Preview
@Composable
fun preview() {
    HassinTmdbTaskTheme {
        MovieItem(movie = Movie1(posterPath = "", title = "Test")){}
    }
}

fun Movie1(posterPath: String, title: String): Movie {
    return Movie(
        adult = false,
        title = title,
        posterPath = posterPath,
        backdropPath = "",
        genreIds = listOf(),
        id = 123,
        originalLanguage = "En",
        originalTitle = "FFF",
        overview = "",
        popularity = 9.0,
        releaseDate = "5/5/5",
        video = false,
        voteAverage = 5.0,
        voteCount = 1234
    )
}
