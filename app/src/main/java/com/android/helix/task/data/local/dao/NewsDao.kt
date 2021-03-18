package com.android.helix.task.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.utils.Database
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM ${Database.NEWS_TABLE_NAME}")
    fun readData(): Flow<NewsEntity>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: NewsEntity)

}