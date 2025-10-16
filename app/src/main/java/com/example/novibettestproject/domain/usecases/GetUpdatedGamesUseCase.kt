package com.example.novibettestproject.domain.usecases

import com.example.novibettestproject.domain.models.Game
import com.example.novibettestproject.domain.repositories.AuthenticationRepository
import com.example.novibettestproject.domain.repositories.GamesRepository
import javax.inject.Inject

class GetUpdatedGamesUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(): List<Game>? {
        val authToken = authenticationRepository.getAccessToken()
        if (authToken.value == null) return null
        return gamesRepository.getUpdatedLiveGames()
    }
}