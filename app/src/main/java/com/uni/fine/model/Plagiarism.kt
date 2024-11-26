package com.uni.fine.model

data class Plagiarism(
    val id: String,
    val url: String,
    val probability: PlagiarismProbability
)