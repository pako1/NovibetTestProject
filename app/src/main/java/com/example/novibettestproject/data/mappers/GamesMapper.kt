package com.example.novibettestproject.data.mappers

import com.example.novibettestproject.data.remote.models.GamesDTO
import com.example.novibettestproject.domain.models.Games

object GamesMapper {

    fun mapToDomain(gamesDTO: GamesDTO): Games = with(gamesDTO) {
        Games(awayTeam = awayTeam, homeTeam = homeTeam, elapsedTime = elapsedTime)
    }
}