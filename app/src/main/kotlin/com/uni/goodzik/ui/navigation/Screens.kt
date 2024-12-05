package com.uni.goodzik.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object Splash : Screens

    @Serializable
    data object Auth : Screens
}