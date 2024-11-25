package com.uni.fine.model

import java.time.LocalDate

data class CheckInfo(
    val id: String,
    val title: String,
    val prompt: String,
    val summary: String,
    val createdAt: LocalDate,
    val issues: List<Issue>,
) {
    data class Issue(
        val id: String,
        val type: IssueType,
        val text: String,
        val message: String,
        val suggestion: String,
        val start: Int,
        val end: Int,
    )
}