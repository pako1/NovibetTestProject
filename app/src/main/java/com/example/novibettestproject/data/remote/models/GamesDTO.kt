package com.example.novibettestproject.data.remote.models

import com.squareup.moshi.Json

data class GamesDTO(
    val betViews: List<BetViewDTO>
)

data class BetViewDTO(
    val competitions: List<CompetitionDTO>
)

data class CompetitionDTO(
    val events: List<EventDTO>
)

data class EventDTO(
    val additionalCaptions: AdditionalCaptionsDTO,
    val liveData: LiveDataDTO
)

data class AdditionalCaptionsDTO(
    @Json(name = "competitor1")
    val homeTeam: String,
    @Json(name = "competitor2")
    val awayTeam: String
)

data class LiveDataDTO(
    @Json(name = "elapsed")
    val elapsedTime: String
)