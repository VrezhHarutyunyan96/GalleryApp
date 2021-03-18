package com.android.helix.task.data.local.converter

import androidx.room.TypeConverter
import com.android.helix.task.data.local.entity.NewsEntity
import com.google.gson.Gson

class DataConverter {

    @TypeConverter
    fun newsListToJson(value: List<NewsEntity.NewsItemEntity?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun newsJsonToList(value: String): List<NewsEntity.NewsItemEntity?>? {
        if (Gson().fromJson(value, Array<NewsEntity.NewsItemEntity?>::class.java) != null) {
            val objects = Gson().fromJson(
                value,
                Array<NewsEntity.NewsItemEntity?>::class.java
            ) as Array<NewsEntity.NewsItemEntity?>
            val list = objects.toList()
            return list
        }
        return null
    }

    @TypeConverter
    fun galleryListToJson(value: List<NewsEntity.ImageItemEntity?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun galleryJsonToList(value: String): List<NewsEntity.ImageItemEntity?>? {
        if (Gson().fromJson(value, Array<NewsEntity.ImageItemEntity?>::class.java) != null) {
            val objects = Gson().fromJson(
                value,
                Array<NewsEntity.ImageItemEntity?>::class.java
            ) as Array<NewsEntity.ImageItemEntity?>
            val list = objects.toList()
            return list
        }
        return null
    }

    @TypeConverter
    fun videoListToJson(value: List<NewsEntity.VideoItemEntity?>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun videoJsonToList(value: String): List<NewsEntity.VideoItemEntity?>? {
        if (Gson().fromJson(value, Array<NewsEntity.VideoItemEntity?>::class.java) != null) {
            val objects = Gson().fromJson(
                value,
                Array<NewsEntity.VideoItemEntity?>::class.java
            ) as Array<NewsEntity.VideoItemEntity?>
            val list = objects.toList()
            return list
        }
        return null
    }
}