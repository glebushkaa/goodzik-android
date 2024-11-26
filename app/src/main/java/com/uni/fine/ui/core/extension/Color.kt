package com.uni.fine.ui.core.extension

import androidx.compose.ui.graphics.Color
import com.uni.fine.model.IssueType
import com.uni.fine.model.PlagiarismProbability

fun IssueType.toIssueColor(): Color {
    return when (this) {
        IssueType.Grammar -> Color(0xFFFF7676)
        IssueType.Style -> Color(0xFF7691FF)
        IssueType.Vocabulary -> Color(0xFFFB9B71)
        IssueType.Tone -> Color(0xFFB676FA)
    }
}

fun PlagiarismProbability.toPlagiarismColor(): Color {
    return when (this) {
        PlagiarismProbability.Low -> Color(0xFFA2C796)
        PlagiarismProbability.Medium -> Color(0xFFFFB350)
        PlagiarismProbability.High -> Color(0xFFFF7676)
    }
}