package com.della.hassintmdbtask

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.della.hassintmdbtask.compose.MovieDetail
import com.della.hassintmdbtask.compose.PopularMoviesScreen

@Composable
fun AppNavHost(navHostController: NavHostController = rememberNavController()) {

    NavHost(navController = navHostController, startDestination = "popular_movies" ){

        composable(route = "popular_movies"){
            PopularMoviesScreen(){
                navHostController.navigate("movie_detail/$it")
            }

        }

        composable(route = "movie_detail/{movieId}",
        arguments = listOf(
            navArgument("movieId"){
                type = NavType.IntType
            }
        )
        ){
            MovieDetail(
                onBackClick = {navHostController.navigateUp() }
                , movieId = it.arguments?.getInt("movieId")?:-1 )
        }
    }
}