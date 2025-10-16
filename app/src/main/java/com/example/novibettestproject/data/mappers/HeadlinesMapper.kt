package com.example.novibettestproject.data.mappers

import com.example.novibettestproject.data.remote.models.HeadlinesDTO
import com.example.novibettestproject.domain.models.Headline

object HeadlinesMapper {

    fun mapToDomain(headlinesDTO: HeadlinesDTO): List<Headline> =
        headlinesDTO.headlines.map { (homeTeam, awayTeam, startTime) ->
            Headline(homeTeam = homeTeam, awayTeam = awayTeam, startTime = startTime)
        }
}