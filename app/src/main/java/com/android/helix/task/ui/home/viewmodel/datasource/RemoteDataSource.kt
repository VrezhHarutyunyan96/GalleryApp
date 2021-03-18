package com.android.helix.task.ui.home.viewmodel.datasource

import com.android.helix.task.data.remote.ApiService

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getNews() = apiService.getNews()

}