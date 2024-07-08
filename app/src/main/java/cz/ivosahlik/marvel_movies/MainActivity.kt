package cz.ivosahlik.marvel_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.ivosahlik.marvel_movies.ui.theme.MarvelmoviesTheme
import cz.ivosahlik.marvel_movies.view.CharacterDetailScreen
import cz.ivosahlik.marvel_movies.view.CharactersBottomNav
import cz.ivosahlik.marvel_movies.view.CollectionScreen
import cz.ivosahlik.marvel_movies.view.LibraryScreen

sealed class Destination(val route: String) {
    object Library: Destination("library")
    object Collection: Destination("collection")
    object CharacterDetail: Destination("character/{characterId}") {
        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelmoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    CharacterScaffold(navController = navController)
                }
            }
        }
    }
}

@Composable
fun CharacterScaffold(navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold (
        topBar = { TopBar() },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { CharactersBottomNav(navController = navController) },
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {
            NavHost(navController = navController,
                startDestination = Destination.Library.route)
            {
                composable(Destination.Library.route) {
                    LibraryScreen()
                }
                composable(Destination.Collection.route) {
                    CollectionScreen()
                }
                composable(Destination.CharacterDetail.route) {
                    CharacterDetailScreen()
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        backgroundColor = colorResource(id = R.color.gradientDark),
        contentColor = Color.White
    )
}