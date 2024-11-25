package com.uni.fine.ui.screens.info

sealed interface InfoAction {
    data class IssueClicked(val issueId: String) : InfoAction
    data object HideIssue : InfoAction
}