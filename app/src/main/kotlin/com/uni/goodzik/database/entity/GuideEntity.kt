package com.uni.goodzik.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("guide_entity")
data class GuideEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("video")
    val video: String?,
    @ColumnInfo("date")
    val date: String,
)

@Entity("guide_images_entity")
data class GuideImagesEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("guide_id")
    val guideId: String,
    @ColumnInfo("image")
    val image: String,
)