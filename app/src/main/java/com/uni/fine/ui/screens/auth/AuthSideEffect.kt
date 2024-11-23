package com.uni.fine.ui.screens.auth

sealed interface AuthSideEffect {
    object Back : AuthSideEffect
}