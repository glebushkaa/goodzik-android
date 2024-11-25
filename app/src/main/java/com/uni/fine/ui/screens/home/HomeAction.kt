package com.uni.fine.ui.screens.home

sealed interface HomeAction {
    data object CreateCheckClicked : HomeAction
    data object LogOutClicked : HomeAction
}