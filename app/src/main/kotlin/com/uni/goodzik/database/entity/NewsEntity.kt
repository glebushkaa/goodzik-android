package com.uni.goodzik.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("news_table")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("author")
    val author: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("date")
    val date: String,
)