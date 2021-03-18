package com.android.helix.task.ui.home.model

import com.squareup.moshi.Json

data class NewsResponseModel(

	@Json(name="metadata")
	val newsItem: List<NewsItem?>? = null,

	@Json(name="internal_errors")
	val internalErrors: List<Any?>? = null,

	@Json(name="success")
	val success: Boolean? = null,

	@Json(name="errors")
	val errors: List<Any?>? = null
)

data class GalleryItem(

	@Json(name="contentUrl")
	val contentUrl: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="thumbnailUrl")
	val thumbnailUrl: String? = null
)

data class VideoItem(

	@Json(name="youtubeId")
	val youtubeId: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="thumbnailUrl")
	val thumbnailUrl: String? = null
)

data class NewsItem(

	@Json(name="date")
	val date: Int? = null,

	@Json(name="coverPhotoUrl")
	val coverPhotoUrl: String? = null,

	@Json(name="shareUrl")
	val shareUrl: String? = null,

	@Json(name="category")
	val category: String? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="body")
	val body: String? = null,

	@Json(name="gallery")
	val gallery: List<GalleryItem?>? = null,

	@Json(name="video")
	val video: List<VideoItem?>? = null
)
