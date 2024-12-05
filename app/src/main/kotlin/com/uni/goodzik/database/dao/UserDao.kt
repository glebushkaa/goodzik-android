package com.uni.goodzik.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.uni.goodzik.database.entity.UserEntity

@Dao
interface UserDao {

    @Upsert
    suspend fun upsert(user: UserEntity)

    @Query("SELECT token FROM user")
    suspend fun getToken(): String?

    @Query("DELETE FROM user")
    suspend fun delete()
}