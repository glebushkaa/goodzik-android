package com.uni.goodzik.ui.screens.details

import com.uni.goodzik.model.Message

data class DetailsState(
    val title: String = "",
    val author: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val exampleImages: List<String> = emptyList(),
    val schemes: List<String> = emptyList(),
    val isStepsEmpty: Boolean = true,
    val comments: List<Message> = emptyList(),
    val videoUrl: String? = null,
    val messageText: String = "",
)