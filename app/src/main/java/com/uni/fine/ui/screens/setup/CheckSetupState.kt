package com.uni.fine.ui.screens.setup

import com.uni.fine.model.CheckStyle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class CheckSetupState(
    val topic: String = "",
    val style: CheckStyle = CheckStyle.Neutral,
    val styleOptions: ImmutableList<CheckStyle> = CheckStyle.entries.toImmutableList(),
    val excludedWords: String = "",
    val buttonEnabled: Boolean = false
)