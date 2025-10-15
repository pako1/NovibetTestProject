package com.example.novibettestproject.data.remote.api

import com.example.novibettestproject.data.remote.models.GamesDTO
import com.example.novibettestproject.data.remote.models.HeadlinesDTO
import com.example.novibettestproject.data.remote.models.Token
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface NovibetApi {

    @GET()
    suspend fun getAccessToken(): Token

    @GET()
    suspend fun getLiveGames(): GamesDTO

    @GET()
    suspend fun getHeadlines(): HeadlinesDTO

    @GET()
    suspend fun getUpdatedLiveGames(): Flow<GamesDTO>

    @GET()
    suspend fun getUpdatedHeadlines(): Flow<HeadlinesDTO>
}