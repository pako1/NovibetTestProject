package com.example.novibettestproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.novibettestproject.domain.models.Game
import com.example.novibettestproject.domain.models.Headline
import com.example.novibettestproject.domain.usecases.GetGamesUseCase
import com.example.novibettestproject.domain.usecases.GetHeadlinesUseCase
import com.example.novibettestproject.presentation.adapter.HeadlineItem
import com.example.novibettestproject.presentation.adapter.Item
import com.example.novibettestproject.presentation.mappers.UIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val getHeadlinesUseCase: GetHeadlinesUseCase
) : ViewModel() {

    private val _games: MutableStateFlow<List<Item.Game>?> = MutableStateFlow(null)
    val games = _games.asStateFlow()

    private val _headlines: MutableStateFlow<List<HeadlineItem>?> = MutableStateFlow(null)
    val headlines = _headlines.asStateFlow()

    fun getGames() = viewModelScope.launch {
        _games.update {
            getGamesUseCase()
                ?.map { game: Game ->
                    UIMapper.transform(game)
                }
        }
    }

    fun getHeadlines() = viewModelScope.launch {
        _headlines.update {
            getHeadlinesUseCase()
                ?.map { headline: Headline ->
                    UIMapper.transform(headline)
                }
        }
    }


    fun updateHeadlines() {}
    fun updateGames() {}
}


// TODO
//  3. Create horizontal recyclerview for headlines
//  4. Update every X interval
//  5. Write unit tests