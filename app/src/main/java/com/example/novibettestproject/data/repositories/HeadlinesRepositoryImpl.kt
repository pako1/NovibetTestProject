package com.example.novibettestproject.data.repositories

import com.example.novibettestproject.data.mappers.HeadlinesMapper
import com.example.novibettestproject.data.remote.api.NovibetApi
import com.example.novibettestproject.domain.models.Headlines
import com.example.novibettestproject.domain.repositories.HeadlinesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HeadlinesRepositoryImpl @Inject constructor(
    private val novibetApi: NovibetApi
) : HeadlinesRepository {
    override suspend fun getHeadlines(): Headlines {
        val headlines = novibetApi
            .getHeadlines()
            .let { headlinesDTO ->
                HeadlinesMapper.mapToDomain(headlinesDTO)
            }

        return headlines
    }

    override suspend fun getUpdatedHeadlines(): Flow<Headlines> = novibetApi
        .getUpdatedHeadlines()
        .map { headlinesDTO -> HeadlinesMapper.mapToDomain(headlinesDTO) }
}