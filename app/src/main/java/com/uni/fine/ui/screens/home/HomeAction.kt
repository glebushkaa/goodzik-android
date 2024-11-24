package com.uni.fine.ui.screens.home

sealed interface HomeAction {
    data class CheckClicked(val checkId: String) : HomeAction
    data object CreateCheckClicked : HomeAction
    data object LogOutClicked : HomeAction
}