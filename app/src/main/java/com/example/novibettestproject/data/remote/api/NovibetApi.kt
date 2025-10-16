package com.example.novibettestproject.data.remote.api

import com.example.novibettestproject.data.remote.models.GamesDTO
import com.example.novibettestproject.data.remote.models.HeadlinesDTO
import com.example.novibettestproject.data.remote.models.Token
import retrofit2.http.GET

interface NovibetApi {

    @GET(TOKEN)
    suspend fun getAccessToken(): Token

    @GET(LIVE_GAMES)
    suspend fun getLiveGames(): List<GamesDTO>

    @GET(UPDATED_LIVE_GAMES)
    suspend fun getUpdatedLiveGames(): List<GamesDTO>

    @GET(HEADLINES)
    suspend fun getHeadlines(): List<HeadlinesDTO>

    @GET(UPDATED_HEADLINES)
    suspend fun getUpdatedHeadlines(): List<HeadlinesDTO>
}