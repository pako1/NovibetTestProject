package com.example.novibettestproject.data.mappers

import com.example.novibettestproject.data.remote.models.HeadlinesDTO
import com.example.novibettestproject.domain.models.Headlines

object HeadlinesMapper {

    fun mapToDomain(headlinesDTO: HeadlinesDTO): Headlines = with(headlinesDTO) {
        Headlines(homeTeam = homeTeam, awayTeam = awayTeam, startTime = startTime)
    }
}