package com.uni.fine.network.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginModel(
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String
)