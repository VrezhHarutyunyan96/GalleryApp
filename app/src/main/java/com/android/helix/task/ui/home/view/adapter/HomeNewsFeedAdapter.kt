package com.android.helix.task.ui.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.databinding.ItemNewsBinding
import java.sql.Timestamp
import java.util.*

class HomeNewsFeedAdapter(private val onClick: (newsDetail: NewsEntity.NewsItemEntity) -> Unit) :
    RecyclerView.Adapter<HomeNewsFeedAdapter.HomeNewsFeedViewHolder>() {

    private lateinit var newsFeed: NewsEntity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNewsFeedViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeNewsFeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeNewsFeedViewHolder, position: Int) {
        newsFeed.newsItem?.get(position)?.let {
            holder.bind(it, onClick)
        }
    }

    override fun getItemCount(): Int {
        return newsFeed.newsItem?.size ?: 0
    }


    fun setNewsFeed(newsFeed: NewsEntity) {
        this.newsFeed = newsFeed
        notifyDataSetChanged()
    }


    class HomeNewsFeedViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            newsItem: NewsEntity.NewsItemEntity,
            onClick: (newsDetail: NewsEntity.NewsItemEntity) -> Unit
        ) {
            val timestamp = Timestamp(newsItem.date?.toLong()!!)
            binding.apply {
                date = Date(timestamp.time).toString()
                this.newsItem = newsItem
                card.setOnClickListener {
                    onClick(newsItem)
                }
            }

        }
    }

}
