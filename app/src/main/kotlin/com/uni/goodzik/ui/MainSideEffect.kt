package com.uni.goodzik.ui

sealed interface MainSideEffect {
    data object LogOut : MainSideEffect
}