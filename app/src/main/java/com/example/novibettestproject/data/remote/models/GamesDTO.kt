package com.example.novibettestproject.data.remote.models

import com.squareup.moshi.Json

data class GamesDTO(
    @Json(name = "competitor1")
    val homeTeam: String,
    @Json(name = "competitor2")
    val awayTeam: String,
    @Json(name = "Elapsed")
    val elapsedTime: String
)