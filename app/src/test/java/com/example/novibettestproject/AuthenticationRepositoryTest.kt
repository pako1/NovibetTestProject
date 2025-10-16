package com.example.novibettestproject

import com.example.novibettestproject.data.remote.api.NovibetApi
import com.example.novibettestproject.data.remote.api.TokenProvider
import com.example.novibettestproject.data.remote.models.Token
import com.example.novibettestproject.data.repositories.AuthenticationRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AuthenticationRepositoryImplTest {

    private lateinit var novibetApi: NovibetApi
    private lateinit var tokenProvider: TokenProvider
    private lateinit var repository: AuthenticationRepositoryImpl

    private val fakeToken = Token("12345")

    @Before
    fun setUp() {
        novibetApi = mockk()
        tokenProvider = mockk()
        repository = AuthenticationRepositoryImpl(novibetApi, tokenProvider)
    }

    @Test
    fun `getAccessToken returns token from provider if available`() = runTest {
        coEvery { tokenProvider.isTokenAvailable() } returns true
        coEvery { tokenProvider.getToken() } returns fakeToken

        val result = repository.getAccessToken()

        assertEquals(fakeToken, result)
        coVerify(exactly = 0) { novibetApi.getAccessToken() }
        coVerify(exactly = 0) { tokenProvider.setToken(any()) }
    }

    @Test
    fun `getAccessToken fetches token from API if not available`() = runTest {
        coEvery { tokenProvider.isTokenAvailable() } returns false
        coEvery { novibetApi.getAccessToken() } returns fakeToken
        coEvery { tokenProvider.setToken(fakeToken) } returns Unit

        val result = repository.getAccessToken()

        assertEquals(fakeToken, result)
        coVerify(exactly = 1) { novibetApi.getAccessToken() }
        coVerify(exactly = 1) { tokenProvider.setToken(fakeToken) }
    }
}