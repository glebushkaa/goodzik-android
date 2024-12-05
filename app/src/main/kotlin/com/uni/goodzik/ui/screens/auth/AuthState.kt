package com.uni.goodzik.ui.screens.auth

data class AuthState(
    val type: AuthType = AuthType.Login,
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val buttonEnabled: Boolean = false,
) {
    enum class AuthType { Login, Register }
}