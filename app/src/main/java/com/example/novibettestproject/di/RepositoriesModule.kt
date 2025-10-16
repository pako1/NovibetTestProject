package com.example.novibettestproject.di

import com.example.novibettestproject.data.remote.api.NovibetApi
import com.example.novibettestproject.data.remote.api.TokenProvider
import com.example.novibettestproject.data.repositories.AuthenticationRepositoryImpl
import com.example.novibettestproject.data.repositories.GamesRepositoryImpl
import com.example.novibettestproject.data.repositories.HeadlinesRepositoryImpl
import com.example.novibettestproject.domain.repositories.AuthenticationRepository
import com.example.novibettestproject.domain.repositories.GamesRepository
import com.example.novibettestproject.domain.repositories.HeadlinesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideGamesRepository(novibetApi: NovibetApi): GamesRepository =
        GamesRepositoryImpl(novibetApi = novibetApi)

    @Provides
    @Singleton
    fun provideHeadlinesRepository(novibetApi: NovibetApi): HeadlinesRepository =
        HeadlinesRepositoryImpl(novibetApi = novibetApi)

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        novibetApi: NovibetApi,
        tokenProvider: TokenProvider
    ): AuthenticationRepository = AuthenticationRepositoryImpl(
        novibetApi = novibetApi,
        tokenProvider = tokenProvider
    )
}