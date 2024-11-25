package com.uni.fine.ui.screens.setup

sealed interface CheckSetupSideEffect {
    data object Next: CheckSetupSideEffect
}