package com.android.helix.task.ui.home.viewmodel.datasource

import com.android.helix.task.data.local.dao.NewsDao
import com.android.helix.task.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class LocalDataSource(private val newsDao: NewsDao) {

    fun saveNews(newsEntity: NewsEntity) = runBlocking {
        return@runBlocking newsDao.insert(newsEntity)
    }

    fun loadNews(): Flow<NewsEntity> {
        return newsDao.readData()
    }

}