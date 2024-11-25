package com.uni.fine.ui.screens.info

import com.uni.fine.model.CheckInfo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class InfoState(
    val text: String = "",
    val issues: ImmutableList<CheckInfo.Issue> = persistentListOf(),
    val selectedIssue: CheckInfo.Issue? = null,
    val loading: Boolean = false,
)