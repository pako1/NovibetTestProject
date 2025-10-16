package com.example.novibettestproject.domain.repositories

import com.example.novibettestproject.domain.models.Headline

interface HeadlinesRepository {

    suspend fun getHeadlines(): List<Headline>
    suspend fun getUpdatedHeadlines(): List<Headline>
}