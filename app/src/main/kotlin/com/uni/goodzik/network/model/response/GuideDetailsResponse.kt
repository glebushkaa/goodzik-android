package com.uni.goodzik.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuideDetailsResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("author")
    val author: ProfileResponse,
    @SerialName("exampleImages")
    val exampleImages: List<String>,
    @SerialName("videoUrl")
    val videoUrl: String,
    @SerialName("comments")
    val comments: List<Comment>,
    @SerialName("steps")
    val steps: List<Step>
) {
    @Serializable
    data class Step(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("description")
        val description: String,
        @SerialName("image")
        val image: String,
        @SerialName("order")
        val order: Int
    )

    @Serializable
    data class Comment(
        @SerialName("id")
        val id: String,
        @SerialName("text")
        val text: String,
        @SerialName("author")
        val author: String,
    )
}