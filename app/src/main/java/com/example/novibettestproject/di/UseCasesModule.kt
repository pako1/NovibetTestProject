package com.example.novibettestproject.di

import com.example.novibettestproject.domain.repositories.AuthenticationRepository
import com.example.novibettestproject.domain.repositories.GamesRepository
import com.example.novibettestproject.domain.repositories.HeadlinesRepository
import com.example.novibettestproject.domain.usecases.GetGamesUseCase
import com.example.novibettestproject.domain.usecases.GetHeadlinesUseCase
import com.example.novibettestproject.domain.usecases.GetUpdatedGamesUseCase
import com.example.novibettestproject.domain.usecases.GetUpdatedHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetGamesUseCase(
        gamesRepository: GamesRepository,
        authenticationRepository: AuthenticationRepository
    ): GetGamesUseCase = GetGamesUseCase(
        authenticationRepository = authenticationRepository,
        gamesRepository = gamesRepository
    )

    @Provides
    fun provideGetHeadlinesUseCase(
        headlinesRepository: HeadlinesRepository,
        authenticationRepository: AuthenticationRepository
    ): GetHeadlinesUseCase = GetHeadlinesUseCase(
        authenticationRepository = authenticationRepository,
        headlinesRepository = headlinesRepository
    )

    @Provides
    fun provideGetUpdatedGamesUseCase(
        gamesRepository: GamesRepository,
        authenticationRepository: AuthenticationRepository
    ): GetUpdatedGamesUseCase = GetUpdatedGamesUseCase(
        authenticationRepository = authenticationRepository,
        gamesRepository = gamesRepository
    )

    @Provides
    fun provideGetUpdatedHeadlinesUseCase(
        headlinesRepository: HeadlinesRepository,
        authenticationRepository: AuthenticationRepository
    ): GetUpdatedHeadlinesUseCase = GetUpdatedHeadlinesUseCase(
        authenticationRepository = authenticationRepository,
        headlinesRepository = headlinesRepository
    )
}