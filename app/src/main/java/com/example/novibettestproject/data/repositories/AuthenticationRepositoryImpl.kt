package com.example.novibettestproject.data.repositories

import com.example.novibettestproject.data.remote.api.NovibetApi
import com.example.novibettestproject.data.remote.models.Token
import com.example.novibettestproject.domain.repositories.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val novibetApi: NovibetApi) :
    AuthenticationRepository {
    override suspend fun getAccessToken(): Token = novibetApi.getAccessToken()

}