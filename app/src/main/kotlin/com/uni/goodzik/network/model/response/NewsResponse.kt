package com.uni.goodzik.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val news: List<NewsItemResponse> = emptyList()
) {
    @Serializable
    data class NewsItemResponse(
        @SerialName("id")
        val id: String,
        @SerialName("title")
        val title: String,
        @SerialName("description")
        val description: String,
        @SerialName("author")
        val author: String,
        @SerialName("image")
        val images: List<String>,
        @SerialName("date")
        val date: String
    )
}