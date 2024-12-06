package com.uni.goodzik.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uni.goodzik.database.dao.GuideDao
import com.uni.goodzik.database.dao.NewsDao
import com.uni.goodzik.database.dao.UserDao
import com.uni.goodzik.database.entity.CommentEntity
import com.uni.goodzik.database.entity.GuideEntity
import com.uni.goodzik.database.entity.GuideImagesEntity
import com.uni.goodzik.database.entity.NewsEntity
import com.uni.goodzik.database.entity.NewsImagesEntity
import com.uni.goodzik.database.entity.StepsEntity
import com.uni.goodzik.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class, GuideEntity::class, NewsEntity::class,
        NewsImagesEntity::class, StepsEntity::class, CommentEntity::class,
        GuideImagesEntity::class
    ],
    version = 1
)
abstract class GuideDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun guideDao(): GuideDao
    abstract fun newsDao(): NewsDao
}