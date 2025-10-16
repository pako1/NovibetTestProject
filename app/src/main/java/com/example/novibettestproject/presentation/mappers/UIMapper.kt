package com.example.novibettestproject.presentation.mappers

import com.example.novibettestproject.domain.models.Game
import com.example.novibettestproject.domain.models.Headline
import com.example.novibettestproject.presentation.adapter.HeadlineItem
import com.example.novibettestproject.presentation.adapter.Item

object UIMapper {
    fun transform(headline: Headline) = with(headline) {
        HeadlineItem(homeTeam = homeTeam, awayTeam = awayTeam, startTime = startTime)
    }

    fun transform(game: Game) = with(game) {
        Item.Game(
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            elapsedTime = elapsedTime
        )
    }
}