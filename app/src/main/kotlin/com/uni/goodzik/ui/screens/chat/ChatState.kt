package com.uni.goodzik.ui.screens.chat

import com.uni.goodzik.model.Message
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class ChatState(
    val messages: ImmutableList<Message> = persistentListOf(),
    val text: String = ""
)