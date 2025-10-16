package com.example.novibettestproject.data.repositories

import com.example.novibettestproject.data.mappers.HeadlinesMapper
import com.example.novibettestproject.data.remote.api.NovibetApi
import com.example.novibettestproject.domain.models.Headline
import com.example.novibettestproject.domain.repositories.HeadlinesRepository
import javax.inject.Inject

class HeadlinesRepositoryImpl @Inject constructor(
    private val novibetApi: NovibetApi
) : HeadlinesRepository {

    override suspend fun getHeadlines(): List<Headline> {
        val headlines = novibetApi
            .getHeadlines()
            .flatMap { headlinesDTO -> HeadlinesMapper.mapToDomain(headlinesDTO) }

        return headlines
    }

    override suspend fun getUpdatedHeadlines(): List<Headline> = novibetApi
        .getUpdatedHeadlines()
        .flatMap { headlinesDTO -> HeadlinesMapper.mapToDomain(headlinesDTO) }
}