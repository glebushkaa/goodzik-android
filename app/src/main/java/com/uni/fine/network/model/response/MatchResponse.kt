package com.uni.fine.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchResponse(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("score")
    val score: Double,
)