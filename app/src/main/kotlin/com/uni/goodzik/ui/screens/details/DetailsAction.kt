package com.uni.goodzik.ui.screens.details

sealed interface DetailsAction {

    data class MessageUpdated(val message: String) : DetailsAction
    data object Send : DetailsAction
    data class DownloadFile(
        val url: String,
        val order: Int
    ) : DetailsAction
}