package com.android.helix.task.ui.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.ui.home.model.NewsItem

class DetailsSharedVIewModel : ViewModel() {

    private val newsSharedMutableLiveData: MutableLiveData<NewsEntity.NewsItemEntity> by lazy {
        return@lazy MutableLiveData<NewsEntity.NewsItemEntity>()
    }

    fun setNews(newsItem: NewsEntity.NewsItemEntity) {
        newsSharedMutableLiveData.value = newsItem
    }

    fun getNews(): LiveData<NewsEntity.NewsItemEntity> = newsSharedMutableLiveData
}