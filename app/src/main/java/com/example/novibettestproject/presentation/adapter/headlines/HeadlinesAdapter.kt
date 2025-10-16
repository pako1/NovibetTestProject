package com.example.novibettestproject.presentation.adapter.headlines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.novibettestproject.databinding.ItemSingleHeadlineBinding
import com.example.novibettestproject.presentation.adapter.HeadlineItem
import com.example.novibettestproject.presentation.adapter.headlines.HeadlinesAdapter.HeadlineViewHolder

class HeadlinesAdapter : ListAdapter<HeadlineItem, HeadlineViewHolder>(HeadlinesDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val binding =
            ItemSingleHeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeadlineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HeadlineViewHolder(private val binding: ItemSingleHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(headline: HeadlineItem) = with(binding) {
            homeTeam.text = headline.homeTeam
            awayTeam.text = headline.awayTeam
            startTime.text = headline.startTime
        }
    }
}