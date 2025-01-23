package com.project.animeapp.data.repository

import Data
import com.project.animeapp.common.ResultWrapper
import com.project.animeapp.common.safeApiCall
import com.project.animeapp.data.response.anime_details.Details
import com.project.animeapp.domain.IGetAnimeRepository
import com.project.animeapp.network.APIService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAnimeRepository @Inject constructor(
    private val apiService: APIService
) : IGetAnimeRepository {
    override suspend fun getAnime(): ResultWrapper<List<Data>>  {
        return safeApiCall(Dispatchers.IO) {
            val response = apiService.getAnime()
           response.data
        }
    }

    override suspend fun getAnimeById(id: Int): ResultWrapper<Details> {
        return safeApiCall(Dispatchers.IO){
            val response = apiService.getAnimeDetail(id)
            response.data
        }
    }
}