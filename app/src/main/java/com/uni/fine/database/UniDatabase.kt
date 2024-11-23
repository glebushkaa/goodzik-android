package com.uni.fine.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uni.fine.database.dao.UserDao
import com.uni.fine.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UniDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}