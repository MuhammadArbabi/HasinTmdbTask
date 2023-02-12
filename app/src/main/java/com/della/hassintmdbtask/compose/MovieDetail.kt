package com.della.hassintmdbtask.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.della.hassintmdbtask.viewmodel.MovieDetailViewModel


@Composable
fun MovieDetail(
    onBackClick: ()->Unit,
    movieId: Int,
    modifier: Modifier = Modifier,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val rememberedMovieId by remember {
        mutableStateOf(movieId)
    }
    val movieDetail = movieDetailViewModel.movieDetail.value

    LaunchedEffect(key1 = rememberedMovieId) {
        movieDetailViewModel.getDetailOfMovie(rememberedMovieId)
    }

    movieDetail?.let { movie ->
       MovieInfoScreen(movie = movie){
           onBackClick()
       }

    }
}


/*
@Composable
private fun MovieInfo1(
    movie: MovieDetailResponse,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF180E36))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.33F)
        ) {
            val (
                backdropImage,
                backButton,
                movieTitleBox,
                moviePosterImage,
                translucentBr
            ) = createRefs()

            val backDropPainter = rememberAsyncImagePainter(
                model = movie.backdropPath.toBackdropUrl(),
                error = rememberVectorPainter(Icons.Filled.BrokenImage),
                placeholder = rememberVectorPainter(Icons.Default.Movie)
            )
            val backDropScale =
                if (backDropPainter.state !is AsyncImagePainter.State.Success) ContentScale.Fit else ContentScale.FillBounds

            Image(
                painter = backDropPainter,
                contentDescription = stringResource(
                    id = com.della.hassintmdbtask.R.string.backdrop_content_description,
                ),
                contentScale = backDropScale,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .constrainAs(backdropImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            )

            Button(
                onClick = { onBackClick() },
                modifier = Modifier
                    .constrainAs(backButton) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                    }) {
                Text(text = "<-")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color(0XFF180E36).copy(alpha = 0.5F),
                                Color(0XFF180E36)
                            ),
                            startY = 0.1F
                        )
                    )
                    .constrainAs(translucentBr) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(backdropImage.bottom)
                    }
            )

            Column(
                modifier = Modifier.constrainAs(movieTitleBox) {
                    start.linkTo(moviePosterImage.end, margin = 12.dp)
                    end.linkTo(parent.end, margin = 12.dp)
                    bottom.linkTo(moviePosterImage.bottom, margin = 10.dp)
                },
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = movie.title,
                    modifier = Modifier
                        .padding(top = 2.dp, start = 4.dp, bottom = 4.dp)
                        .fillMaxWidth(0.5F),
                    maxLines = 2,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White.copy(alpha = 0.78F)
                )
            }

            val posterPainter = rememberAsyncImagePainter(
                model = movie.posterPath.toPosterUrl(),
                error = rememberVectorPainter(Icons.Filled.BrokenImage),
                placeholder = rememberVectorPainter(Icons.Default.Movie)
            )
            val posterScale =
                if (posterPainter.state !is AsyncImagePainter.State.Success) ContentScale.Fit else ContentScale.FillBounds

            Image(
                painter = posterPainter,
                contentDescription = stringResource(
                    id = com.della.hassintmdbtask.R.string.poster_content_description,
                ),
                contentScale = posterScale,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .width(115.dp)
                    .height(172.5.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .constrainAs(moviePosterImage) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
            )

        }


    }
}

*             LazyRow(
                modifier = Modifier
                    .padding(top = (96).dp, bottom = 4.dp, start = 4.dp, end = 4.dp)
                    .fillMaxWidth()
            ) {
                val filmGenres: List<Genre> = homeViewModel.filmGenres.filter { genre ->
                    return@filter if (film.genreIds.isNullOrEmpty()) false else
                        film.genreIds!!.contains(genre.id)
                }
                filmGenres.forEach { genre ->
                    item {
                        MovieGenreChip(
                            background = ButtonColor,
                            textColor = AppOnPrimaryColor,
                            genre = genre.name
                        )
                    }
                }
            }

            ExpandableText(
                text = film.overview,
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 4.dp, start = 4.dp, end = 4.dp)
                    .fillMaxWidth()
            )

            LazyColumn(
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    AnimatedVisibility(visible = (filmCastList.isNotEmpty())) {
                        Text(
                            text = "Cast",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = AppOnPrimaryColor,
                            modifier = Modifier.padding(start = 4.dp, top = 6.dp, bottom = 4.dp)
                        )
                    }
                }
                item {
                    LazyRow(modifier = Modifier.padding(4.dp)) {
                        filmCastList.forEach { cast ->
                            item { CastMember(cast = cast) }
                        }
                    }
                }
                item {
                    if (similarFilms.itemCount != 0) {
                        Text(
                            text = "Similar",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = AppOnPrimaryColor,
                            modifier = Modifier.padding(start = 4.dp, top = 6.dp, bottom = 4.dp)
                        )
                    }
                }

                item {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(similarFilms) { thisMovie ->
                            CoilImage(
                                imageModel = "${BASE_POSTER_IMAGE_URL}/${thisMovie!!.posterPath}",
                                shimmerParams = ShimmerParams(
                                    baseColor = AppPrimaryColor,
                                    highlightColor = ButtonColor,
                                    durationMillis = 500,
                                    dropOff = 0.65F,
                                    tilt = 20F
                                ),
                                failure = {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.image_not_available),
                                            contentDescription = "no image"
                                        )
                                    }
                                },
                                previewPlaceholder = R.drawable.popcorn,
                                contentScale = ContentScale.Crop,
                                circularReveal = CircularReveal(duration = 1000),
                                modifier = Modifier
                                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .size(130.dp, 195.dp)
                                    .clickable {
                                        film = thisMovie
                                    },
                                contentDescription = "Movie item"
                            )
                        }
                    }
                }
            }*/