package com.uni.goodzik.model

import java.time.LocalDate

data class Guide(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val date: LocalDate,
    val categories: List<Category>,
    val videoUrl: String?
)