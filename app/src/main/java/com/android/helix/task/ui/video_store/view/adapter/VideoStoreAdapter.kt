package com.android.helix.task.ui.video_store.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.databinding.ItemVideoBinding
import com.android.helix.task.utils.getYoutubeUrl

class VideoStoreAdapter(private val onClick: (url: String) -> Unit) :
    RecyclerView.Adapter<VideoStoreAdapter.VideoStoreViewHolder>() {

    private lateinit var newsItem: NewsEntity.NewsItemEntity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoStoreViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideoStoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoStoreViewHolder, position: Int) {
        newsItem.video?.get(position)?.let {
            holder.bind(it, onClick)
        }
    }

    override fun getItemCount(): Int {
        return newsItem.video?.size ?: 0
    }

    fun addVideoItems(newsItem: NewsEntity.NewsItemEntity) {
        this.newsItem = newsItem
    }

    class VideoStoreViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            videoItem: NewsEntity.VideoItemEntity,
            onClick: (url: String) -> Unit
        ) {
            binding.videoItem = videoItem
            binding.card.setOnClickListener {
                val data = videoItem.youtubeId + ""
                onClick(data.getYoutubeUrl())
            }
        }
    }

}