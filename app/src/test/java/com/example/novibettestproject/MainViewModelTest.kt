package com.example.novibettestproject

import app.cash.turbine.test
import com.example.novibettestproject.domain.models.Game
import com.example.novibettestproject.domain.models.Headline
import com.example.novibettestproject.domain.usecases.GetGamesUseCase
import com.example.novibettestproject.domain.usecases.GetHeadlinesUseCase
import com.example.novibettestproject.domain.usecases.GetUpdatedGamesUseCase
import com.example.novibettestproject.domain.usecases.GetUpdatedHeadlinesUseCase
import com.example.novibettestproject.presentation.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainViewModel

    private val getGamesUseCase: GetGamesUseCase = mockk()
    private val getHeadlinesUseCase: GetHeadlinesUseCase = mockk()
    private val getUpdatedGamesUseCase: GetUpdatedGamesUseCase = mockk()
    private val getUpdatedHeadlinesUseCase: GetUpdatedHeadlinesUseCase = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(
            getGamesUseCase,
            getHeadlinesUseCase,
            getUpdatedGamesUseCase,
            getUpdatedHeadlinesUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getGames_updates_games_stateflow_correctly() = runTest {
        val gameDomain = Game(homeTeam = "Team A", awayTeam = "Team B", elapsedTime = "45'")
        coEvery { getGamesUseCase() } returns listOf(gameDomain)

        viewModel.getGames()
        testDispatcher.scheduler.advanceUntilIdle() //fast forward all emissions until there is nothing left

        viewModel.games.test { //collect items and await for each emiyssion
            val value = awaitItem()
            assertEquals(1, value?.size)
            val game = value?.first()
            assertEquals("Team A", game?.homeTeam)
            assertEquals("Team B", game?.awayTeam)
            assertEquals("45'", game?.elapsedTime)
            cancelAndConsumeRemainingEvents() // stop and consume
        }
    }

    @Test
    fun getHeadlines_updates_headlines_stateflow_correctly() = runTest {
        val headlineDomain = Headline(homeTeam = "Home", awayTeam = "Away", startTime = "20:00")
        coEvery { getHeadlinesUseCase() } returns listOf(headlineDomain)

        viewModel.getHeadlines()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.headlines.test {
            val value = awaitItem()
            assertEquals(1, value?.size)
            val headlineItem = value?.first()
            assertEquals("Home", headlineItem?.homeTeam)
            assertEquals("Away", headlineItem?.awayTeam)
            assertEquals("20:00", headlineItem?.startTime)
            cancelAndConsumeRemainingEvents()
        }
    }
}