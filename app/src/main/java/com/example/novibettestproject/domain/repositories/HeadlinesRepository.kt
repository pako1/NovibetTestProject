package com.example.novibettestproject.domain.repositories

import com.example.novibettestproject.domain.models.Headline
import kotlinx.coroutines.flow.Flow

interface HeadlinesRepository {

    suspend fun getHeadlines(): List<Headline>
    suspend fun getUpdatedHeadlines(): Flow<List<Headline>>
}