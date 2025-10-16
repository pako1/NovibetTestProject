package com.example.novibettestproject.domain.repositories

import com.example.novibettestproject.data.remote.models.Token

interface AuthenticationRepository {

    suspend fun getAccessToken(): Token
}