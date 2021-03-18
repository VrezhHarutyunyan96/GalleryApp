package com.android.helix.task.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object NetworkUtil : ConnectivityManager.NetworkCallback() {

    private val networkLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun checkNetwork(context: Context) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(this)
        } else {
            connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), this)
        }

        var isAvailable = false

        // Retrieve current status of connectivity
        connectivityManager.allNetworks.forEach { network ->
            val networkCapability = connectivityManager.getNetworkCapabilities(network)
            networkCapability?.let {
                if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                    isAvailable = true
                    return@forEach
                }
            }
        }
        networkLiveData.postValue(if (!isAvailable) null else isAvailable )
    }

    fun getNetworkLiveData() : LiveData<Boolean> = networkLiveData

    override fun onAvailable(network: Network) {
        networkLiveData.postValue(true)
    }

    override fun onUnavailable() {
        networkLiveData.postValue(null)
    }

    override fun onLost(network: Network) {
        networkLiveData.postValue(false)
    }
}