package com.android.helix.task

import android.app.Application
import com.android.helix.task.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    init {
        instance = this
    }

    companion object {
        var isInternetAvailable: Boolean? = false
        var instance: AppApplication? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                networkModule,
                dataSourceModule,
                repositoryModule,
                dbModule,
                presentationModule
            )
        }
    }
}