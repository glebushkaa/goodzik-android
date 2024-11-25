package com.uni.fine.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object Splash : Screens

    @Serializable
    data object Auth : Screens

    @Serializable
    data object Home : Screens

    @Serializable
    data object CheckSetup : Screens

    @Serializable
    data object UploadWork : Screens

    @Serializable
    data class Info(
        val id: String,
        val new: Boolean
    ) : Screens
}