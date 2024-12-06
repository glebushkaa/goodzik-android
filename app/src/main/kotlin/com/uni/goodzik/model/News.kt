package com.uni.goodzik.model

import java.time.LocalDate

data class News(
    val id: String,
    val title: String,
    val images: List<String> = emptyList(),
    val description: String,
    val author: String,
    val date: LocalDate,
    val categories: List<Category> = emptyList()
)