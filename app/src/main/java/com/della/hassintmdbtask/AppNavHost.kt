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
import com.della.hassintmdbtask.viewmodel.SharedViewModel

/**
 * Defines a composable function AppNavHost in the Compose UI framework. The function takes a navHostController parameter which is an instance of NavHostController and is remembered using the rememberNavController function.

The NavHost composable is then created with the given navController and a start destination of "popular_movies". Within the NavHost, two destinations are defined using the composable function.

The first destination has a route of "popular_movies" and is defined as the PopularMoviesScreen composable. Within this composable, navigation to the "movie_detail" destination is triggered when a movie is selected and the navigate method on the navHostController is called with the movie ID appended to the route.

The second destination has a route of "movie_detail/{movieId}" and takes an argument of "movieId" of type IntType. The destination is defined as the MovieDetail composable, which takes two parameters - onBackClick and movieId. The onBackClick parameter is a lambda expression that triggers navigation up using the navigateUp method on the navHostController. The movieId parameter is obtained from the arguments passed to the composable using the arguments property of the it object.
*/
@Composable
fun AppNavHost(sharedViewModel: SharedViewModel, navHostController: NavHostController = rememberNavController()) {

    NavHost(navController = navHostController, startDestination = "popular_movies" ){

        composable(route = "popular_movies"){
            PopularMoviesScreen(){
                sharedViewModel.selectMovie(it)
                navHostController.navigate("movie_detail/${it.id}")
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
                onBackClick = {navHostController.navigateUp() },
                sharedViewModel = sharedViewModel,
                movieId = it.arguments?.getInt("movieId")?:-1 )
        }
    }
}