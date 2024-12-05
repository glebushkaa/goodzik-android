package com.uni.goodzik.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screens {
    @Serializable
    data object Splash : Screens

    @Serializable
    data object Auth : Screens

    @Serializable
    data object Profile: Screens

    @Serializable
    data object News: Screens

    @Serializable
    data object Guides: Screens

    @Serializable
    data object Donate: Screens

    @Serializable
    data class Details(val id: String) : Screens

    @Serializable
    data class Steps(val id: String) : Screens

    @Serializable
    data class NewsDetails(val id: String) : Screens

    @Serializable
    data class Chat(val id: String) : Screens
}