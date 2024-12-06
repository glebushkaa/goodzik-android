package com.uni.goodzik.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuideResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("author")
    val author: ProfileResponse,
    @SerialName("image")
    val image: String?,
    @SerialName("videoUrl")
    val video: String?,
    @SerialName("date")
    val date: String
)