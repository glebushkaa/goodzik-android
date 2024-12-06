package com.uni.goodzik.domain.repository

import com.uni.goodzik.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(): Flow<List<News>>
    suspend fun getNewsWithImage(id: String): News?
}