package com.uni.fine.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchesRequest(
    @SerialName("checkId")
    val checkId: String,
)