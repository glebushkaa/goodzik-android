package com.uni.goodzik.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class NewsWithImages(
    @Embedded
    val news: NewsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "news_id"
    )
    val images: List<NewsImagesEntity>
)