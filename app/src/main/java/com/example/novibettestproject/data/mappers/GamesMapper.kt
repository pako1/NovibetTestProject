package com.example.novibettestproject.data.mappers

import com.example.novibettestproject.data.remote.models.BetViewDTO
import com.example.novibettestproject.data.remote.models.CompetitionDTO
import com.example.novibettestproject.data.remote.models.EventDTO
import com.example.novibettestproject.data.remote.models.GamesDTO
import com.example.novibettestproject.domain.models.Game

object GamesMapper {

    fun wrapperToDomain(gamesDTO: List<GamesDTO>): List<Game> = gamesDTO
        .flatMap { gamesDTOToDomain(it) }

    private fun gamesDTOToDomain(gamesDTO: GamesDTO): List<Game> = gamesDTO
        .betViews
        .flatMap { betViewDTO ->
            betViewDTOToDomain(betViewDTO)
        }

    private fun betViewDTOToDomain(betViewDTO: BetViewDTO): List<Game> = betViewDTO
        .competitions
        .flatMap { competitionDTO ->
            competitionDTOToDomain(competitionDTO)
        }

    private fun competitionDTOToDomain(competitionDTO: CompetitionDTO): List<Game> =
        competitionDTO.events.map { eventDTO ->
            eventDTOToDomain(eventDTO)
        }

    private fun eventDTOToDomain(eventDTO: EventDTO): Game = Game(
        homeTeam = eventDTO.additionalCaptions.homeTeam,
        awayTeam = eventDTO.additionalCaptions.awayTeam,
        elapsedTime = eventDTO.liveData.elapsedTime
    )
}