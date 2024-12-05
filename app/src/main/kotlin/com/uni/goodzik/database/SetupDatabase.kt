package com.uni.goodzik.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uni.goodzik.database.dao.UserDao
import com.uni.goodzik.database.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class SetupDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}