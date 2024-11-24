package com.uni.fine.model

import java.time.LocalDate

data class Check(
    val id: String,
    val title: String,
    val summary: String,
    val createdAt: LocalDate,
)