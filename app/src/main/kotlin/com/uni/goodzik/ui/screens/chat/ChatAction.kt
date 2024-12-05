package com.uni.goodzik.ui.screens.chat

sealed interface ChatAction {
    data class MessageChanged(val message: String) : ChatAction
    data object Send : ChatAction
}