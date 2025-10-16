package com.example.novibettestproject.domain.usecases

import com.example.novibettestproject.domain.models.Headline
import com.example.novibettestproject.domain.repositories.AuthenticationRepository
import com.example.novibettestproject.domain.repositories.HeadlinesRepository
import javax.inject.Inject

class GetUpdatedHeadlinesUseCase @Inject constructor(
    private val headlinesRepository: HeadlinesRepository,
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(): List<Headline>? {
        val authToken = authenticationRepository.getAccessToken()
        if (authToken.value == null) return null
        return headlinesRepository.getUpdatedHeadlines()
    }
}