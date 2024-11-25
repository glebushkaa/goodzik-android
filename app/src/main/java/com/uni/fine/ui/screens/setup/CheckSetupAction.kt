package com.uni.fine.ui.screens.setup

import com.uni.fine.model.CheckStyle

sealed interface CheckSetupAction {
    data class TopicChanged(val topic: String) : CheckSetupAction
    data class StyleChanged(val style: CheckStyle) : CheckSetupAction
    data class ExcludedWordsChanged(val excludedWords: String) : CheckSetupAction
    data object Next : CheckSetupAction
}