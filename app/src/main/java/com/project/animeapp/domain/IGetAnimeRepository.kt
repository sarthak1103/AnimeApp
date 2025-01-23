package com.project.animeapp.domain

import Data
import com.project.animeapp.common.ResultWrapper
import com.project.animeapp.data.response.anime_details.Details
import javax.inject.Singleton

@Singleton
interface IGetAnimeRepository {
    suspend fun getAnime(): ResultWrapper<List<Data>>
    suspend fun getAnimeById(id: Int): ResultWrapper<Details>
}