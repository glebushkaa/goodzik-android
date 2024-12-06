package com.uni.goodzik.network.api

import com.uni.goodzik.network.model.response.NewsResponse
import retrofit2.http.GET

interface NewsApi {

    @GET("news")
    suspend fun getNews(): NewsResponse
}