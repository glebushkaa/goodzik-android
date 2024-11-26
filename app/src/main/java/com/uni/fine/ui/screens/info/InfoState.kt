package com.uni.fine.ui.screens.info

import com.uni.fine.model.CheckInfo
import com.uni.fine.model.Plagiarism
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class InfoState(
    val text: String = "",
    val issues: ImmutableList<CheckInfo.Issue> = persistentListOf(),
    val plagiarisms: ImmutableList<Plagiarism> = persistentListOf(),
    val selectedIssue: CheckInfo.Issue? = null,
    val sheet: InfoViewModel.Sheet? = null,
    val buttonLoading: Boolean = false,
    val buttonVisible: Boolean = true,
    val aiScore: Int = 0
)