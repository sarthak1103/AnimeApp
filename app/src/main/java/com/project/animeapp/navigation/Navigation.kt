package com.project.animeapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.animeapp.ui.features.anime.detail.AnimeDetailScreen
import com.project.animeapp.ui.features.anime.home.HomeScreen
import com.project.animeapp.ui.features.anime.AnimeViewModel

@Composable
fun Navigation(
    navController: NavHostController,
) {
    val animeViewModel: AnimeViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.ANIME_LIST,
    ) {
        composable(
            route = Screen.ANIME_LIST,
        ) {
            HomeScreen(animeViewModel = animeViewModel,
                onItemClick = {id->
                    navController.navigate(Screen.ANIME_DETAILS + "/${id}")
                }
            )
        }
        composable(
            route = Screen.ANIME_DETAILS + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            AnimeDetailScreen(
                animeViewModel = animeViewModel,
                id = it.arguments?.getInt("id") ?: 0
            )
        }
    }
}