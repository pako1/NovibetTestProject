import com.example.novibettestproject.data.remote.models.Token
import com.example.novibettestproject.domain.models.Game
import com.example.novibettestproject.domain.repositories.AuthenticationRepository
import com.example.novibettestproject.domain.repositories.GamesRepository
import com.example.novibettestproject.domain.usecases.GetGamesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetGamesUseCaseTest {

    private val gamesRepository: GamesRepository = mockk()
    private val authenticationRepository: AuthenticationRepository = mockk()
    private val getGamesUseCase = GetGamesUseCase(gamesRepository, authenticationRepository)

    @Test
    fun `invoke returns null when access token value is null`() = runBlocking {
        val token = mockk<Token>()
        coEvery { authenticationRepository.getAccessToken() } returns token
        coEvery { token.value } returns null

        val result = getGamesUseCase()
        assertEquals(null, result)
    }

    @Test
    fun `invoke returns games when access token value is not null`() = runBlocking {
        val token = mockk<Token>()
        coEvery { authenticationRepository.getAccessToken() } returns token
        coEvery { token.value } returns "token123"

        val gameList = listOf(Game("Team A", "Team B", "45'"))
        coEvery { gamesRepository.getGames() } returns gameList

        val result = getGamesUseCase()
        assertEquals(gameList, result)
    }
}