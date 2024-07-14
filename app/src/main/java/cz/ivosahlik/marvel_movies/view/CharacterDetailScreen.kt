package cz.ivosahlik.marvel_movies.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import cz.ivosahlik.marvel_movies.CharacterImage
import cz.ivosahlik.marvel_movies.Destination
import cz.ivosahlik.marvel_movies.comicsToString
import cz.ivosahlik.marvel_movies.viewmodel.LibraryApiViewModel

@Composable
fun CharacterDetailScreen(
    navController: NavHostController,
    libraryApiViewModel: LibraryApiViewModel,
    paddingValues: PaddingValues
) {
    val character = libraryApiViewModel.characterDetails.value

    if (character == null) {
        navController.navigate(Destination.Library.route) {
            popUpTo(Destination.Library.route)
            launchSingleTop = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
            .padding(bottom = paddingValues.calculateBottomPadding())
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageUrl = character?.thumbnail?.path + "." + character?.thumbnail?.extension
        val title = character?.name ?: "No name"
        val comics = character?.comics?.items?.mapNotNull { it.name }?.comicsToString() ?: "No comics"
        val description = character?.description ?: "No description"
        val context = LocalContext.current
        CharacterImage(
            url = imageUrl, modifier = Modifier
                .width(200.dp)
                .padding(4.dp)
        )

        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(4.dp)
        )

        Text(
            text = comics,
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            modifier = Modifier.padding(4.dp)
        )

        Text(text = description, fontSize = 16.sp, modifier = Modifier.padding(4.dp))

        Button(onClick = {
            Toast
                .makeText(context,"TODO....", Toast.LENGTH_SHORT)
                .show()
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            modifier = Modifier.padding(bottom = 20.dp))
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Check, contentDescription = null)
                Text(text = "Added", color = Color.Red)
            }
        }
    }
}