package com.uni.goodzik.model

data class Message(
    val id: String,
    val text: String,
    val incoming: Boolean,
    val author: String,
)