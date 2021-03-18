package com.android.helix.task.utils

import com.android.helix.task.data.local.entity.NewsEntity
import com.android.helix.task.ui.home.model.NewsResponseModel

interface Mapper<I, O> {

    fun map(model: I): O
}


class NewsToEntityMapper : Mapper<NewsResponseModel, NewsEntity> {

    override fun map(model: NewsResponseModel): NewsEntity {

        model.newsItem?.let {

            val newsItemsList = mutableListOf<NewsEntity.NewsItemEntity>()

            for (item in model.newsItem) {

                item?.let { newsItem ->

                    val videoEntityModelList = mutableListOf<NewsEntity.VideoItemEntity>()
                    newsItem.video?.let {
                        for (videoItem in newsItem.video) {
                            val videoEntityModel = NewsEntity.VideoItemEntity(
                                videoItem?.youtubeId,
                                videoItem?.title,
                                videoItem?.thumbnailUrl
                            )
                            videoEntityModelList.add(videoEntityModel)
                        }
                    }

                    val imageEntityModelList = mutableListOf<NewsEntity.ImageItemEntity>()
                    newsItem.gallery?.let {
                        for (imageItem in newsItem.gallery) {
                            val imageItemEntityModel = NewsEntity.ImageItemEntity(
                                imageItem?.contentUrl,
                                imageItem?.title,
                                imageItem?.thumbnailUrl
                            )
                            imageEntityModelList.add(imageItemEntityModel)
                        }
                    }

                    val entityModel = NewsEntity.NewsItemEntity(
                        newsItem.date,
                        newsItem.coverPhotoUrl,
                        newsItem.shareUrl,
                        newsItem.category,
                        newsItem.title,
                        newsItem.body,
                        imageEntityModelList,
                        videoEntityModelList
                    )
                    newsItemsList.add(entityModel)
                }

            }
            return NewsEntity(
                newsItemsList,
                model.success,
            )
        }

        return NewsEntity()
    }

}
