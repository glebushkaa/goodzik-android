package com.uni.fine.ui.screens.upload

sealed interface UploadSideEffect {
    data class Next(val id: String) : UploadSideEffect
}