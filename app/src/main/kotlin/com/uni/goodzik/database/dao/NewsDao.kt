package com.uni.goodzik.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.uni.goodzik.database.entity.NewsEntity
import com.uni.goodzik.database.entity.NewsImagesEntity
import com.uni.goodzik.database.entity.NewsWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news_table")
    fun getNewsFlow(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news_table WHERE id = :id")
    suspend fun getNewsById(id: String): NewsWithImages?

    @Upsert
    suspend fun upsertNews(news: List<NewsEntity>)

    @Upsert
    suspend fun upsertNewsWithImages(news: List<NewsImagesEntity>)
}