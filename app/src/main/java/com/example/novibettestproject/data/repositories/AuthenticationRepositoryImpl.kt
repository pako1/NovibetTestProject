package com.example.novibettestproject.data.repositories

import com.example.novibettestproject.data.remote.api.NovibetApi
import com.example.novibettestproject.data.remote.api.TokenProvider
import com.example.novibettestproject.data.remote.models.Token
import com.example.novibettestproject.domain.repositories.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val novibetApi: NovibetApi,
    private val tokenProvider: TokenProvider
) : AuthenticationRepository {

    override suspend fun getAccessToken(): Token {
        if (tokenProvider.isTokenAvailable()) return tokenProvider.getToken()
        val authenticationToken = novibetApi.getAccessToken()
        tokenProvider.setToken(authenticationToken)
        return authenticationToken
    }
}