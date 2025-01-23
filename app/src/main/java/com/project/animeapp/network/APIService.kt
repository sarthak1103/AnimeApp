package com.project.animeapp.network

import GetAnimeResponse
import com.project.animeapp.common.APIConstant
import com.project.animeapp.data.response.anime_details.AnimeDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    @GET(APIConstant.CONST_ANIME)
    suspend fun getAnime(): GetAnimeResponse

    @GET(APIConstant.ANIME_DETAIL)
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): AnimeDetails

}