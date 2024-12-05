package com.uni.goodzik.ui.screens.profile

sealed interface ProfileAction {
    data object LogOut : ProfileAction
}