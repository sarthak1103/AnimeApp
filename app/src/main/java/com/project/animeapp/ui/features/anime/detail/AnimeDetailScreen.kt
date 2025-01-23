package com.project.animeapp.ui.features.anime.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.project.animeapp.data.response.anime_details.Genre
import com.project.animeapp.ui.features.anime.AnimeEvents
import com.project.animeapp.ui.features.anime.AnimeViewModel
import com.project.animeapp.ui.features.mediaPlayer.YoutubePlayer

@Composable
fun AnimeDetailScreen(
    animeViewModel: AnimeViewModel,
    id: Int,
) {
    val context = LocalContext.current
    val state = animeViewModel.state.value
    val playerView = remember {
        mutableStateOf(YouTubePlayerView(context))
    }
    val lifecycle = remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(key1 = Unit) {
        animeViewModel.onEvent(AnimeEvents.GetAnimeById(id))
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle.value = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            playerView.value.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    if (state.getAnimeById.isNotEmpty()) {
        val anime = state.getAnimeById[0]
        val verticalScrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticalScrollState)
                .background(Color.Black)

        ) {
            Text(
                text = state.getAnimeById[0].title,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (anime.trailer.youtubeId.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(236.dp)
                        .padding(bottom = 16.dp)
                ) {
                    YoutubePlayer(
                        youtubeVideoId = anime.trailer.youtubeId,
                        lifecycleOwner = LocalLifecycleOwner.current,
                        playerView = playerView.value
                    )
                }

            } else {
                AsyncImage(
                    model = anime.images.jpg.imageUrl,
                    contentDescription = "",
                    modifier = Modifier.fillMaxHeight()
                )
            }
            GenreSection(genres = anime.genres)
            Text(
                text = "Ratings: ${anime.rating}",
                modifier = Modifier
                    .padding(8.dp),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Episodes:${anime.episodes}",
                modifier = Modifier
                    .padding(8.dp),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Synopsis:${anime.synopsis}",
                modifier = Modifier
                    .padding(8.dp),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }


}

@Composable
fun GenreSection(genres: List<Genre>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Genre",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Red
        )
        genres.forEach { genre ->
            Text(
                text = genre.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )

        }
    }

}
