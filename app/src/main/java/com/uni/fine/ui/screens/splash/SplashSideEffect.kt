package com.uni.fine.ui.screens.splash

sealed interface SplashSideEffect {
    data object NavigateToHome : SplashSideEffect
    data object NavigateToAuth : SplashSideEffect
}