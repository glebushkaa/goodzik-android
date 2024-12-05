package com.uni.goodzik.ui.screens.steps

import com.uni.goodzik.model.Step
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class StepsState(
    val steps: ImmutableList<Step> = persistentListOf()
)