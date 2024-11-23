package com.uni.fine.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("token")
    val token: String,
)