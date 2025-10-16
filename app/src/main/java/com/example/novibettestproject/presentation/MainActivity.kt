package com.example.novibettestproject.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.novibettestproject.R
import com.example.novibettestproject.databinding.ActivityMainBinding
import com.example.novibettestproject.presentation.adapter.HeadlineItem
import com.example.novibettestproject.presentation.adapter.Item
import com.example.novibettestproject.presentation.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyWindowInsets()
        initMainAdapter()
        observe()
        mainViewModel.getHeadlines()
        mainViewModel.getGames()
        mainViewModel.updateHeadlines()
        mainViewModel.updateGames()
    }

    private fun observe() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                combine(
                    mainViewModel.headlines,
                    mainViewModel.games
                )
                { headlines, games ->
                    Pair(headlines, games)
                }.collect { (headlines, games) ->
                    populateData(headlines, games)
                }
            }
        }
    }

    private fun populateData(headlines: List<HeadlineItem>?, games: List<Item.Game>?) {
        val items = buildList {
            add(headlines?.let { Item.Headline(it) })
            games?.let { addAll(it) }
        }
        mainAdapter.submitList(items.filterNotNull())
        if (headlines.isNullOrEmpty()) return
        startAutoScroll()
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startAutoScroll() {
        lifecycleScope.launch {
            while (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                delay(SWIPE_INTERVAL)
                val headlineHolder = binding.recycler
                    .findViewHolderForAdapterPosition(0) as? MainAdapter.HeadlineViewHolder
                headlineHolder?.scrollNext()
            }
        }
    }

    private fun initMainAdapter() = with(binding.recycler) {
        mainAdapter = MainAdapter()
        adapter = mainAdapter
    }

    private companion object {
        const val SWIPE_INTERVAL = 5000L
    }
}