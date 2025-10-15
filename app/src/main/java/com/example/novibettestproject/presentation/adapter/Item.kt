package com.example.novibettestproject.presentation.adapter

sealed class Item {
    data class Headline(
        val homeTeam: String,
        val awayTeam: String,
        val startTime: String
    ) : Item()

    data class Game(
        val homeTeam: String,
        val homeTeamScore: String,
        val awayTeam: String,
        val awayTeamScore: String,
        val elapsedTime: String
    ) : Item()
}