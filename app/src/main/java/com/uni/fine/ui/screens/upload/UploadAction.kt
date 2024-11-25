package com.uni.fine.ui.screens.upload

sealed interface UploadAction {
    data object SendAction : UploadAction
    data class TextChanged(val text: String) : UploadAction
    data class FilePicked(
        val fileName: String,
        val path: String
    ) : UploadAction

    data object Clear : UploadAction
}