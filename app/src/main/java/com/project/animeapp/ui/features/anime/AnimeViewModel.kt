package com.project.animeapp.ui.features.anime

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.animeapp.common.ResultWrapper
import com.project.animeapp.data.repository.GetAnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeRepository: GetAnimeRepository
) : ViewModel() {
    private val _state = mutableStateOf(AnimeState())
    val state: State<AnimeState> = _state

    fun onEvent(event: AnimeEvents) {
        when (event) {
            is AnimeEvents.GetAllAnime -> {
                viewModelScope.launch {
                    getAllAnime()
                }
            }

            is AnimeEvents.GetAnimeById -> {
                viewModelScope.launch {
                    getAnimeById(event.id)
                }
            }
        }
    }

    private suspend fun getAllAnime() {
        _state.value = _state.value.copy(isLoading = true)
        when (val result = getAnimeRepository.getAnime()) {
            is ResultWrapper.GenericError -> {
                Log.d("AnimeViewModel", "Generic Error: ${result.message}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.message ?: "An unknown error occurred"
                )
            }

            is ResultWrapper.NetworkError -> {
                Log.d("AnimeViewModel", "Network Error: ${result.message}")
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = result.message.ifEmpty { "Network error. Please check your connection." }
                )
            }

            is ResultWrapper.Success -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    data = result.value
                )
                Log.d("AnimeViewModel", "Anime List: ${result.value}")
            }
        }
    }

    private suspend fun getAnimeById(
        animeId: Int,
    ) {
        _state.value = _state.value.copy(isLoading = true)
        when (val getAnimeDetails = getAnimeRepository.getAnimeById(
            id = animeId
        )) {
            is ResultWrapper.GenericError -> {
                _state.value = _state.value.copy(isLoading = false)
                Log.d("AnimeViewModel", "Generic Error: ${getAnimeDetails.message}")
            }

            is ResultWrapper.NetworkError -> {
                _state.value = _state.value.copy(isLoading = false)
                Log.d("AnimeViewModel", "Network Error:")

            }

            is ResultWrapper.Success -> {
                _state.value = _state.value.copy(
                    isLoading = false,
                    getAnimeById = listOf(getAnimeDetails.value)
                )
                Log.d("AnimeViewModel", "Anime Details: ${getAnimeDetails.value}")
            }

        }

    }
}