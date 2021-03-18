package com.android.helix.task.di

import android.app.Application
import androidx.room.Room
import com.android.helix.task.BuildConfig
import com.android.helix.task.data.local.AppDataBase
import com.android.helix.task.data.local.dao.NewsDao
import com.android.helix.task.data.remote.ApiService
import com.android.helix.task.ui.details.viewmodel.DetailsSharedVIewModel
import com.android.helix.task.ui.details.viewmodel.DetailsViewModel
import com.android.helix.task.ui.home.viewmodel.HomeViewModel
import com.android.helix.task.ui.home.viewmodel.datasource.LocalDataSource
import com.android.helix.task.ui.home.viewmodel.datasource.RemoteDataSource
import com.android.helix.task.ui.home.viewmodel.repository.HomeRepository
import com.android.helix.task.ui.home.viewmodel.repository.HomeRepositoryImpl
import com.android.helix.task.utils.Database
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Koin Module
 * */

private const val TIME_OUT = 30L

val networkModule = module {
    single { createRetrofit(get(), BuildConfig.BASE_URL) }
    single { createOkHttpClient() }
    single { createService(get()) }

}

val dataSourceModule = module {
    single { provideRemoteDataSource(get()) }
    single { provideLocalDataSource(get()) }
}

val repositoryModule = module {
    single { provideHomeRepository(get(), get()) }
}

val dbModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideNewsDao(get()) }
}

val presentationModule = module {
    viewModel { provideHomeViewModel(get()) }
    viewModel { provideDetailsViewModel() }
    viewModel { provideNewsDetailsSharedViewModel() }
}

private fun provideNewsDetailsSharedViewModel(): DetailsSharedVIewModel {
    return DetailsSharedVIewModel()
}

private fun provideHomeViewModel(homeRepository: HomeRepository): HomeViewModel {
    return HomeViewModel(homeRepository)
}

private fun provideDetailsViewModel(): DetailsViewModel {
    return DetailsViewModel()
}

private fun provideDatabase(application: Application): AppDataBase {
    return Room.databaseBuilder(application, AppDataBase::class.java, Database.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideNewsDao(appDatabase: AppDataBase): NewsDao {
    return appDatabase.newsDao
}

private fun provideHomeRepository(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
): HomeRepository {
    return HomeRepositoryImpl(remoteDataSource, localDataSource)
}

private fun provideLocalDataSource(newsDao: NewsDao): LocalDataSource {
    return LocalDataSource(newsDao)
}

private fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
    return RemoteDataSource(apiService)
}

fun createOkHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .baseUrl(url)
        .client(okHttpClient)
        .build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}