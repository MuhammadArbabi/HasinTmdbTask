package com.della.hassintmdbtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.della.hassintmdbtask.compose.PopularMoviesScreen
import com.della.hassintmdbtask.ui.theme.HassinTmdbTaskTheme
import com.della.hassintmdbtask.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val sharedViewModel : SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HassinTmdbTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavHost(sharedViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HassinTmdbTaskTheme {

    }
}