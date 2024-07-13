package cz.ivosahlik.marvel_movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.ivosahlik.marvel_movies.ui.theme.MarvelmoviesTheme
import cz.ivosahlik.marvel_movies.view.CharacterDetailScreen
import cz.ivosahlik.marvel_movies.view.CollectionScreen
import cz.ivosahlik.marvel_movies.view.LibraryScreen
import cz.ivosahlik.marvel_movies.viewmodel.LibraryApiViewModel
import dagger.hilt.android.AndroidEntryPoint

sealed class Destination(val route: String) {
    object Library: Destination("library")
    object Collection: Destination("collection")
    object CharacterDetail: Destination("character/{characterId}") {
        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val libraryApiViewModel by viewModels<LibraryApiViewModel>()
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
                    CharacterScaffold(navController = navController, libraryApiViewModel)
                }
            }
        }
    }
}

@Composable
fun CharacterScaffold(
    navController: NavHostController,
    libraryApiViewModel: LibraryApiViewModel)
{
    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold (
        topBar = { TopBar() },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { NavigationBar {
            BottomNavigationItem().bottomNavigationItems().forEachIndexed {index, navigationItem ->
                NavigationBarItem(
                    selected = index == navigationSelectedItem,
                    label = {
                        Text(navigationItem.label)
                    },
                    icon = {
                        Icon(
                            navigationItem.icon,
                            contentDescription = navigationItem.label
                        )
                    },
                    onClick = {
                        navigationSelectedItem = index
                        navController.navigate(navigationItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        } },
    ) { paddingValues ->
        NavHost(navController = navController,
            startDestination = Destination.Library.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Destination.Library.route) {
                LibraryScreen(navController, libraryApiViewModel, paddingValues)
            }
            composable(Destination.Collection.route) {
                CollectionScreen(paddingValues)
            }
            composable(Destination.CharacterDetail.route) { navBackStackEntry ->
                CharacterDetailScreen(paddingValues)
            }
        }
    }
}

//initializing the data class with default parameters
data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Destination.Library.route
            ),
            BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = Destination.Collection.route
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.gradientDark)
        )
    )
}