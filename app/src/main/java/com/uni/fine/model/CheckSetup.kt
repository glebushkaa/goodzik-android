package com.uni.fine.model

data class CheckSetup(
    val topic: String,
    val style: CheckStyle,
    val excludedWords: String
)