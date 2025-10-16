package com.example.novibettestproject

import com.example.novibettestproject.data.mappers.GamesMapper
import com.example.novibettestproject.data.remote.models.AdditionalCaptionsDTO
import com.example.novibettestproject.data.remote.models.BetViewDTO
import com.example.novibettestproject.data.remote.models.CompetitionDTO
import com.example.novibettestproject.data.remote.models.EventDTO
import com.example.novibettestproject.data.remote.models.GamesDTO
import com.example.novibettestproject.data.remote.models.LiveDataDTO
import com.example.novibettestproject.domain.models.Game
import org.junit.Assert.*
import org.junit.Test

class GamesMapperTest {

    @Test
    fun `wrapperToDomain maps nested DTOs correctly`() {
        val event1 = EventDTO(
            additionalCaptions = AdditionalCaptionsDTO("TeamA", "TeamB"),
            liveData = LiveDataDTO("01:23:45.678")
        )

        val event2 = EventDTO(
            additionalCaptions = AdditionalCaptionsDTO("TeamC", "TeamD"),
            liveData = LiveDataDTO("-01:23:45.678") // negative test case
        )

        val competition = CompetitionDTO(events = listOf(event1, event2))
        val betView = BetViewDTO(competitions = listOf(competition))
        val gamesDTO = GamesDTO(betViews = listOf(betView))

        val result: List<Game> = GamesMapper.wrapperToDomain(listOf(gamesDTO))

        assertEquals(2, result.size)

        val first = result[0]
        assertEquals("TeamA", first.homeTeam)
        assertEquals("TeamB", first.awayTeam)
        assertEquals("01:23:45", first.elapsedTime)

        val second = result[1]
        assertEquals("TeamC", second.homeTeam)
        assertEquals("TeamD", second.awayTeam)
        assertEquals("00:00:00", second.elapsedTime)
    }
}