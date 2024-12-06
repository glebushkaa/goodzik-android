package com.uni.goodzik.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("comment_entity")
data class CommentEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("guide_id")
    val guideId: String,
    @ColumnInfo("text")
    val text: String,
    @ColumnInfo("author")
    val author: String,
)