package com.uni.goodzik.data.mapper

import com.uni.goodzik.database.entity.NewsEntity
import com.uni.goodzik.database.entity.NewsWithImages
import com.uni.goodzik.model.News
import com.uni.goodzik.network.model.response.NewsResponse
import com.uni.goodzik.ui.core.extension.toLocalDate

fun NewsResponse.NewsItemResponse.toEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        title = title,
        description = description,
        date = date,
        author = author
    )
}

fun NewsEntity.toDomain(): News {
    return News(
        id = id,
        title = title,
        description = description,
        date = date.toLocalDate(),
        author = author
    )
}

fun NewsWithImages.toDomain(): News {
    return News(
        id = news.id,
        title = news.title,
        description = news.description,
        date = news.date.toLocalDate(),
        images = this.images.map { it.imageUrl },
        author = news.author
    )
}