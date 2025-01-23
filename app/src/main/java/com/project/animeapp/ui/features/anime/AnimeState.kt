package com.project.animeapp.ui.features.anime

import Data
import com.project.animeapp.data.response.anime_details.Details

data class AnimeState(
    val isLoading: Boolean = false,
    val data: List<Data> = emptyList(),
    val getAnimeById: List<Details> = emptyList(),
    val error: String = ""
)