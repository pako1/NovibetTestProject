package com.example.novibettestproject.presentation.adapter.headlines

import androidx.recyclerview.widget.DiffUtil
import com.example.novibettestproject.presentation.adapter.HeadlineItem

object HeadlinesDiffUtil : DiffUtil.ItemCallback<HeadlineItem>() {
    override fun areItemsTheSame(oldItem: HeadlineItem, newItem: HeadlineItem): Boolean {
        return oldItem.homeTeam == newItem.homeTeam &&
                oldItem.awayTeam == newItem.awayTeam &&
                oldItem.startTime == newItem.startTime
    }

    override fun areContentsTheSame(oldItem: HeadlineItem, newItem: HeadlineItem): Boolean {
        return oldItem == newItem
    }
}