package com.uni.goodzik.model

data class GuideDetails(
    val title: String,
    val description: String,
    val author: String,
    val videoUrl: String?,
    val images: List<String>,
    val comments: List<Comment>,
    val steps: List<Step>
)

data class Comment(
    val id: String,
    val author: String,
    val text: String
)