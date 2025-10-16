package com.example.novibettestproject.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.novibettestproject.presentation.adapter.Item.Game
import com.example.novibettestproject.presentation.adapter.Item.Headline

object GamesDiffUtilCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = when {
        oldItem is Headline && newItem is Headline -> true
        oldItem is Game && newItem is Game -> oldItem.homeTeam == newItem.homeTeam && oldItem.awayTeam == newItem.awayTeam
        else -> false
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}