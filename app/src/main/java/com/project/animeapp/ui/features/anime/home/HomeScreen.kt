package com.project.animeapp.ui.features.anime.home

import Data
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.animeapp.ui.features.anime.AnimeEvents
import com.project.animeapp.ui.features.anime.AnimeViewModel

@Composable
fun HomeScreen(
    animeViewModel: AnimeViewModel,
    onItemClick: (Int) -> Unit
) {
    val animeState = animeViewModel.state.value

    LaunchedEffect(key1 = Unit) {
        animeViewModel.onEvent(AnimeEvents.GetAllAnime(isFirstLoaded = true))
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp),
    ) {
        items(animeState.data) { item ->
            AnimeListItem(anime = item) {
                onItemClick(it)
            }
        }
    }

}

@Composable
fun AnimeListItem(
    anime: Data,
    onItemClick: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(8.dp)
            .clickable {
                onItemClick(anime.malId)
            }
    ) {
        AsyncImage(
            model = anime.images.jpg.imageUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = anime.title,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Episodes: ${anime.episodes}",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Rating: ${anime.score}",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}