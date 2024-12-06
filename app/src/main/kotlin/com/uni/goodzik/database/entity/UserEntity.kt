package com.uni.goodzik.database.entity

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
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("email")
    val email: String
)