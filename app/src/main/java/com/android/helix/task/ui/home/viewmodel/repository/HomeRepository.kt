package com.android.helix.task.ui.home.viewmodel.repository

import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.ui.home.model.NewsResponseModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun loadNewsFeed(): Flow<NewsResponseModel>
    suspend fun loadNewsFeedFromDB(): Flow<NewsEntity>
    suspend fun saveNews(entity: NewsEntity)
}