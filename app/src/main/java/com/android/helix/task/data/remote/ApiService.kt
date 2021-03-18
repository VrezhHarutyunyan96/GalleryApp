package com.android.helix.task.data.remote

import com.android.helix.task.ui.home.model.NewsResponseModel
import com.android.helix.task.utils.Network
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(Network.END_POINT)
    suspend fun getNews(): NewsResponseModel
}