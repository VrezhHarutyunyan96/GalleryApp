package com.android.helix.task.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.ui.home.model.NewsResponseModel
import com.android.helix.task.ui.home.viewmodel.repository.HomeRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    private val newsFeedMutableLiveData: MutableLiveData<NewsResponseModel> by lazy {
        return@lazy MutableLiveData()
    }

    private val newsFeedFromDBMutableLiveData: MutableLiveData<NewsEntity> by lazy {
        return@lazy MutableLiveData()
    }

    init {
        loadFromApi()
    }

    fun loadFromApi() = viewModelScope.launch {
        homeRepository.loadNewsFeed().collect {
            newsFeedMutableLiveData.value = it
        }
    }

    fun loadFromDB() = viewModelScope.launch {
        homeRepository.loadNewsFeedFromDB().collect {
            newsFeedFromDBMutableLiveData.value = it
        }
    }

    fun getNewsFeedLiveData() = newsFeedMutableLiveData

    fun getNewsFeedDBLiveData() = newsFeedFromDBMutableLiveData
}