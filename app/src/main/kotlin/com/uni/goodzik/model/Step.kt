package com.uni.goodzik.model

data class Step(
    val id: String,
    val order: Int,
    val guideTitle: String,
    val name: String,
    val description: String,
    val image: String?,
)