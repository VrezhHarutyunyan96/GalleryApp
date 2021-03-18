package com.android.helix.task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.helix.task.data.local.converter.DataConverter
import com.android.helix.task.data.local.dao.NewsDao
import com.android.helix.task.data.local.entity.NewsEntity

@TypeConverters(DataConverter::class)
@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract val newsDao: NewsDao
}