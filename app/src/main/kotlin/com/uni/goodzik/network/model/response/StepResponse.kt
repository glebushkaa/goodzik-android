package com.uni.goodzik.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("order")
    val order: Int
)