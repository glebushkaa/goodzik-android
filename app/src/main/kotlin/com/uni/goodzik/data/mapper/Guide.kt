package com.uni.goodzik.data.mapper

import com.uni.goodzik.database.entity.GuideEntity
import com.uni.goodzik.model.Guide
import com.uni.goodzik.network.model.response.GuideResponse
import com.uni.goodzik.ui.core.extension.toLocalDate

fun GuideResponse.toEntity(): GuideEntity {
    return GuideEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        video = this.video,
        date = this.date,
        author = this.author.name
    )
}

fun GuideEntity.toDomain(): Guide {
    return Guide(
        id = this.id,
        title = this.title,
        description = this.description,
        videoUrl = this.video,
        date = this.date.toLocalDate(),
        categories = emptyList(),
        author = this.author,
    )
}