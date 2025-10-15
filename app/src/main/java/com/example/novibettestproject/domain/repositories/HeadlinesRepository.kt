package com.example.novibettestproject.domain.repositories

import com.example.novibettestproject.domain.models.Headlines
import kotlinx.coroutines.flow.Flow

interface HeadlinesRepository {
    suspend fun getHeadlines(): Headlines
    suspend fun getUpdatedHeadlines(): Flow<Headlines>
}