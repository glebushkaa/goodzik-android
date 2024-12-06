package com.uni.goodzik.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentRequest(
    @SerialName("guideId")
    val guideId: String,
    @SerialName("text")
    val text: String
)