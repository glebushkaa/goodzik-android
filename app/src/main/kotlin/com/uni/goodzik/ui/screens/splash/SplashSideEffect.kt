package com.uni.goodzik.ui.screens.splash

sealed interface SplashSideEffect {
    data object NavigateToHome : SplashSideEffect
    data object NavigateToAuth : SplashSideEffect
}