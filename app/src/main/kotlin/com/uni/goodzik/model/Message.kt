package com.uni.goodzik.model

import java.time.LocalDateTime

data class Message(
    val id: String,
    val text: String,
    val incoming: Boolean,
    val time: LocalDateTime,
)