package com.uni.fine.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uni.fine.model.IssueType

@Entity
data class IssueEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("checkId")
    val checkId: String,
    @ColumnInfo("type")
    val type: IssueType,
    @ColumnInfo("text")
    val text: String,
    @ColumnInfo("message")
    val message: String,
    @ColumnInfo("suggestion")
    val suggestion: String,
    @ColumnInfo("startIndex")
    val start: Int,
    @ColumnInfo("endIndex")
    val end: Int,
)