package com.della.hassintmdbtask.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.della.hassintmdbtask.util.Resource
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
    var rememberedLoadDetail by rememberSaveable {
        mutableStateOf(1)
    }
    val movieDetail = movieDetailViewModel.movieDetail.value


    LaunchedEffect(key1 = rememberedLoadDetail) {
       if(movieDetail !is Resource.Success)
        movieDetailViewModel.getDetailOfMovie(rememberedMovieId)
    }

    when(movieDetail){
        is Resource.Success ->{
            MovieInfoScreen(movie = movieDetail.data!!){
                onBackClick()
            }
        }
        is Resource.Error ->{
            ErrorLoading(message = "Error on loading Detail of movie ...") {
                rememberedLoadDetail++
            }
        }
        else -> {
            LoadingIndicator()
        }
    }
    movieDetail?.let { movie ->


    }
}