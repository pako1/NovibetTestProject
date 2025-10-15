package com.example.novibettestproject.domain.repositories

import com.example.novibettestproject.domain.models.Games
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun getGames(): Games
    suspend fun getUpdatedLiveGames(): Flow<Games>
}