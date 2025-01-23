package com.project.animeapp.ui.features.anime

sealed class AnimeEvents {
    data class GetAllAnime(val isFirstLoaded: Boolean) : AnimeEvents()
    data class GetAnimeById(val id: Int) : AnimeEvents()
}