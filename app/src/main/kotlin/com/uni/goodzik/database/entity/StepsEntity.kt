package com.uni.goodzik.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("steps_entity")
data class StepsEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("guide_id")
    val guideId: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("image")
    val image: String?,
    @ColumnInfo("order")
    val order: Int,
)