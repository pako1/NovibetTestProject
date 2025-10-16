package com.example.novibettestproject.data.repositories

import com.example.novibettestproject.data.mappers.GamesMapper
import com.example.novibettestproject.data.remote.api.NovibetApi
import com.example.novibettestproject.domain.models.Game
import com.example.novibettestproject.domain.repositories.GamesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val novibetApi: NovibetApi,
) : GamesRepository {

    override suspend fun getGames(): List<Game> {
        val games = novibetApi
            .getLiveGames()
            .let { gamesWrapperDTO ->
                GamesMapper.wrapperToDomain(gamesWrapperDTO)
            }

        return games
    }

    override suspend fun getUpdatedLiveGames(): Flow<List<Game>> = novibetApi
        .getUpdatedLiveGames()
        .map { gamesDTO -> GamesMapper.wrapperToDomain(gamesDTO) }
}