package com.uni.goodzik.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.uni.goodzik.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsert(user: UserEntity)

    @Query("SELECT token FROM user")
    suspend fun getToken(): String?

    @Query("DELETE FROM user")
    suspend fun delete()

    @Query("SELECT * FROM user")
    fun getUser(): Flow<UserEntity>

    @Query("UPDATE user SET name = :name, email = :email")
    suspend fun update(name: String, email: String)
}