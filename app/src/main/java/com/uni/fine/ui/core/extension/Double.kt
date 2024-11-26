package com.uni.fine.ui.core.extension

import com.uni.fine.model.PlagiarismProbability

fun Double.toPlagiarismProbability(): PlagiarismProbability {
    return when {
        this < 25.0 -> PlagiarismProbability.Low
        this in 25.0..70.0 -> PlagiarismProbability.Medium
        else -> PlagiarismProbability.High
    }
}