package com.example.novibettestproject.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.novibettestproject.databinding.ItemGameBinding
import com.example.novibettestproject.databinding.ItemHeadlineBinding
import com.example.novibettestproject.presentation.adapter.headlines.HeadlinesAdapter

class MainAdapter : ListAdapter<Item, ViewHolder>(GamesDiffUtilCallback) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is Item.Game -> ViewType.Game.ordinal
        is Item.Headline -> ViewType.Headline.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ViewBinding
        when (viewType) {
            ViewType.Game.ordinal -> {
                binding =
                    ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return GameViewHolder(binding)
            }

            ViewType.Headline.ordinal -> {
                binding =
                    ItemHeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HeadlineViewHolder(binding)
            }

            else -> throw IllegalArgumentException("No such view type available!")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        when (val item = getItem(position)) {
            is Item.Game -> (holder as GameViewHolder).bind(item)
            is Item.Headline -> (holder as HeadlineViewHolder).bind(item)
        }

    private class GameViewHolder(private val itemGameBinding: ItemGameBinding) :
        ViewHolder(itemGameBinding.root) {
        fun bind(game: Item.Game) = with(itemGameBinding) {
            homeTeam.text = game.homeTeam
            awayTeam.text = game.awayTeam
            elapsedTime.text = game.elapsedTime
        }
    }

    class HeadlineViewHolder(private val binding: ItemHeadlineBinding) :
        ViewHolder(binding.root) {

        private val adapter = HeadlinesAdapter()

        init {
            binding.recyclerViewHeadlines.adapter = adapter
        }

        fun scrollNext() = with(binding.recyclerViewHeadlines) {
            val layoutManager = layoutManager as? LinearLayoutManager ?: return@with
            val itemCount = adapter?.itemCount ?: return@with
            val currentPosition = layoutManager.findFirstVisibleItemPosition()
            val nextPosition = currentPosition + 1

            if (nextPosition < itemCount) {
                smoothScrollToPosition(nextPosition)
            }
        }

        fun bind(headline: Item.Headline) = adapter.submitList(headline.headlines)
    }

    private enum class ViewType {
        Game,
        Headline
    }
}
