package com.uni.goodzik.ui.screens.guides

import com.uni.goodzik.model.Guide
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class GuidesState(
    val guides: ImmutableList<Guide> = persistentListOf(),
)