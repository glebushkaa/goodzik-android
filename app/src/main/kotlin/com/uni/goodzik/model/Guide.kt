package com.uni.goodzik.model

import java.time.LocalDate

data class Guide(
    val id: String,
    val title: String,
    val description: String,
    val date: LocalDate,
    val categories: List<Category>
)