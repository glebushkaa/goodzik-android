package com.uni.fine.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("check_entity")
data class CheckEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("prompt")
    val prompt: String,
    @ColumnInfo("summary")
    val summary: String,
    @ColumnInfo("createdAt")
    val createdAt: String,
)