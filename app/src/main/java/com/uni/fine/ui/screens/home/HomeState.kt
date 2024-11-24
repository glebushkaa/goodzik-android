package com.uni.fine.ui.screens.home

import com.uni.fine.model.Check
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class HomeState(
    val checks: ImmutableList<Check> = persistentListOf(),
)