package com.example.novibettestproject

import com.example.novibettestproject.data.mappers.HeadlinesMapper
import com.example.novibettestproject.data.remote.models.HeadlineDTO
import com.example.novibettestproject.data.remote.models.HeadlinesDTO
import com.example.novibettestproject.domain.models.Headline
import org.junit.Assert.assertEquals
import org.junit.Test

class HeadlinesMapperTest {

    @Test
    fun `mapToDomain maps HeadlinesDTO to List of Headline correctly`() {
        val dto = HeadlinesDTO(
            headlines = listOf(
                HeadlineDTO(homeTeam = "TeamA", awayTeam = "TeamB", startTime = "12:30:00"),
                HeadlineDTO(homeTeam = "TeamC", awayTeam = "TeamD", startTime = "15:00:00")
            )
        )

        val result: List<Headline> = HeadlinesMapper.mapToDomain(dto)

        assertEquals(2, result.size)

        val first = result[0]
        assertEquals("TeamA", first.homeTeam)
        assertEquals("TeamB", first.awayTeam)
        assertEquals("12:30:00", first.startTime)

        val second = result[1]
        assertEquals("TeamC", second.homeTeam)
        assertEquals("TeamD", second.awayTeam)
        assertEquals("15:00:00", second.startTime)
    }
}