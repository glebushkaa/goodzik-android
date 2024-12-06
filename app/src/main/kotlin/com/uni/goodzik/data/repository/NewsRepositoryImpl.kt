package com.uni.goodzik.data.repository

import com.uni.goodzik.data.mapper.toDomain
import com.uni.goodzik.data.mapper.toEntity
import com.uni.goodzik.database.dao.NewsDao
import com.uni.goodzik.database.entity.NewsImagesEntity
import com.uni.goodzik.domain.repository.NewsRepository
import com.uni.goodzik.model.News
import com.uni.goodzik.network.api.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun getNews(): Flow<List<News>> = supervisorScope {
        launch {
            val response = newsApi.getNews().news
            val news = response.map { it.toEntity() }
            newsDao.upsertNews(news)
            val newsImages = response.map { entity ->
                entity.images.map { image ->
                    NewsImagesEntity(
                        id = image + entity.id,
                        newsId = entity.id,
                        imageUrl = image
                    )
                }
            }.flatten()
            newsDao.upsertNewsWithImages(newsImages)
        }
        return@supervisorScope newsDao.getNewsFlow().map {
            it.map { news -> news.toDomain() }
        }
    }

    override suspend fun getNewsWithImage(id: String): News? {
        return newsDao.getNewsById(id)?.toDomain()
    }
}