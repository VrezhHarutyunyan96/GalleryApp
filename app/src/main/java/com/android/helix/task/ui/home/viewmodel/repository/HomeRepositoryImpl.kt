package com.android.helix.task.ui.home.viewmodel.repository

import com.android.helix.task.AppApplication
import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.ui.home.viewmodel.datasource.LocalDataSource
import com.android.helix.task.ui.home.viewmodel.datasource.RemoteDataSource
import com.android.helix.task.utils.NewsToEntityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : HomeRepository {

    override suspend fun loadNewsFeed() = flow {
        val isNetworkAvailable = AppApplication.isInternetAvailable
        // check network state
        isNetworkAvailable?.let {
            if (it) {
                val data = remoteDataSource.getNews()
                emit(data)
                val dataDB = NewsToEntityMapper().map(data)
                saveNews(dataDB)
            } else {
                loadNewsFeedFromDB()
            }
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun loadNewsFeedFromDB() = localDataSource.loadNews().flowOn(Dispatchers.IO)

    override suspend fun saveNews(entity: NewsEntity) {
        localDataSource.saveNews(entity)
    }
}

