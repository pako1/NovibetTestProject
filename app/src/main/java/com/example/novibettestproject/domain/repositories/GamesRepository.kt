package com.example.novibettestproject.domain.repositories

import com.example.novibettestproject.domain.models.Game
import kotlinx.coroutines.flow.Flow

interface GamesRepository {

    suspend fun getGames(): List<Game>
    suspend fun getUpdatedLiveGames(): Flow<List<Game>>
}