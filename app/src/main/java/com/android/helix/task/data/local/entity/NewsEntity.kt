package com.android.helix.task.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.android.helix.task.data.local.converter.DataConverter
import com.android.helix.task.ui.home.model.NewsItem
import com.android.helix.task.utils.Database
import com.squareup.moshi.Json

@Entity(tableName = Database.NEWS_TABLE_NAME)

data class NewsEntity(
    @TypeConverters(DataConverter::class)
    @ColumnInfo(name = "metadata")
    val newsItem: List<NewsItemEntity?>? = null,

    val success: Boolean? = null,

) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    data class NewsItemEntity(
        @ColumnInfo(name = "date")
        var date: Int? = null,

        @ColumnInfo(name = "coverPhotoUrl", typeAffinity = ColumnInfo.BLOB)
        var coverPhotoUrl: String? = null,

        @ColumnInfo(name = "shareUrl")
        var shareUrl: String? = null,

        @ColumnInfo(name = "category")
        var category: String? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "body")
        var body: String? = null,

        @TypeConverters(DataConverter::class)
        @ColumnInfo(name = "gallery")
        var gallery: List<ImageItemEntity?>? = null,

        @TypeConverters(DataConverter::class)
        @ColumnInfo(name = "video")
        var video: List<VideoItemEntity?>? = null,


        )


    data class VideoItemEntity(
        @ColumnInfo(name = "youtubeId")
        var youtubeId: String? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "thumbnailUrl")
        var thumbnailUrl: String? = null
    )

    data class ImageItemEntity(
        @ColumnInfo(name = "contentUrl")
        var contentUrl: String? = null,

        @ColumnInfo(name = "title")
        var title: String? = null,

        @ColumnInfo(name = "thumbnailUrl")
        var thumbnailUrl: String? = null
    )

}

