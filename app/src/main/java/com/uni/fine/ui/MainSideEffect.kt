package com.uni.fine.ui

sealed interface MainSideEffect {
    data object LogOut : MainSideEffect
}