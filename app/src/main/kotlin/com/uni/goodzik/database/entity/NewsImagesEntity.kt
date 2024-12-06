package com.uni.goodzik.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("news_images_entity")
data class NewsImagesEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("news_id")
    val newsId: String,
    @ColumnInfo("image_url")
    val imageUrl: String
)