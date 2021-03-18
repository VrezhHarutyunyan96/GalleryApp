package com.android.helix.task.ui.image_store.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.databinding.ItemPhotoBinding

class PhotoStoreAdapter : RecyclerView.Adapter<PhotoStoreAdapter.PhotoStoreViewHolder>() {

    private lateinit var newsItem: NewsEntity.NewsItemEntity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoStoreViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PhotoStoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoStoreViewHolder, position: Int) {
        newsItem.gallery?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return newsItem.gallery?.size ?: 0
    }

    fun addPhotoItems(newsItem: NewsEntity.NewsItemEntity) {
        this.newsItem = newsItem
    }

    class PhotoStoreViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            imageItem: NewsEntity.ImageItemEntity,
        ) {
            binding.imageItem = imageItem
        }
    }
}
