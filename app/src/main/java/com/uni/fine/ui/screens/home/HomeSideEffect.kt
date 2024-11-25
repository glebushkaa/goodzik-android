package com.uni.fine.ui.screens.home

sealed interface HomeSideEffect {
    data object CreateCheck : HomeSideEffect
}