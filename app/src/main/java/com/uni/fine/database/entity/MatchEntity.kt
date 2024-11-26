package com.uni.fine.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("match_entity")
data class MatchEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "checkId")
    val checkId: String,
    @ColumnInfo(name = "source")
    val source: String,
    @ColumnInfo(name = "score")
    val score: Double,
)