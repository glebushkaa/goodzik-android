package com.uni.goodzik.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterModel(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("userName")
    val username: String
)