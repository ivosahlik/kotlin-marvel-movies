package cz.ivosahlik.marvel_movies.view
//
//// TODO: issue, move to material3
////import androidx.compose.material.BottomNavigation
////import androidx.compose.material.BottomNavigationItem
////
////import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import cz.ivosahlik.marvel_movies.Destination
//import cz.ivosahlik.marvel_movies.R
//
//@Composable
//fun CharactersBottomNav(navController: NavHostController) {
//    BottomNavigation(
//        backgroundColor = colorResource(id = R.color.gradientDark),
//        contentColor = Color.White
//    ) {
//        val navBackStackEntry = navController.currentBackStackEntryAsState()
//        val currentDestination = navBackStackEntry.value?.destination
//
//        val iconLibrary = painterResource(id = R.drawable.ic_library)
//        val iconCollection = painterResource(id = R.drawable.ic_collection)
//
//        BottomNavigationItem(
//            selected = currentDestination?.route == Destination.Library.route,
//            onClick = { navController.navigate(Destination.Library.route) {
//                // TODO
//            } },
//            icon = { Icon(painter = iconLibrary, contentDescription = null) },
//            label = { Text(text = Destination.Library.route) }
//        )
//
//        BottomNavigationItem(
//            selected = currentDestination?.route == Destination.Collection.route,
//            onClick = { navController.navigate(Destination.Collection.route) {
//                // TODO
//            } },
//            icon = { Icon(painter = iconCollection, contentDescription = null) },
//            label = { Text(text = Destination.Collection.route) }
//        )
//    }
//}