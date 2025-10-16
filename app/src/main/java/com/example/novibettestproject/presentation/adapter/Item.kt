package com.example.novibettestproject.presentation.adapter

sealed class Item {
    data class Headline(val headlines: List<HeadlineItem>) : Item()

    data class Game(
        val homeTeam: String,
        val awayTeam: String,
        val elapsedTime: String
    ) : Item()
}

data class HeadlineItem(
    val homeTeam: String,
    val awayTeam: String,
    val startTime: String
)