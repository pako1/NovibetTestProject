package com.example.novibettestproject.presentation

import androidx.lifecycle.ViewModel
import com.example.novibettestproject.domain.repositories.GamesRepository
import com.example.novibettestproject.domain.repositories.HeadlinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val headlinesRepository: HeadlinesRepository
) : ViewModel() {

    fun getGames() {}
    fun getHeadlines() {}
    fun updateHeadlines() {}
    fun updateGames() {}
}


// TODO
//  1. Add paths etc on api.
//  2. Fetch authentication token and use it for the remaining api calls
//  3. Create horizontal recyclerview for headlines
//  4. Update every X interval
//  5. Write unit tests