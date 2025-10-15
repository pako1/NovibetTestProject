package com.example.novibettestproject.data.remote.models

import com.squareup.moshi.Json

data class HeadlinesDTO(
    @Json(name = "competitor1Caption")
    val homeTeam: String,
    @Json(name = "competitor2Caption")
    val awayTeam: String,
    val startTime: String
)