package com.example.novibettestproject.domain.repositories

import com.example.novibettestproject.domain.models.Game

interface GamesRepository {

    suspend fun getGames(): List<Game>
    suspend fun getUpdatedLiveGames(): List<Game>
}