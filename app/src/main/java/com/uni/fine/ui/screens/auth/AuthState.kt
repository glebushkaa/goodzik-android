package com.uni.fine.ui.screens.auth

data class AuthState(
    val type: AuthType = AuthType.Login,
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val buttonEnabled: Boolean = false,
) {
    enum class AuthType { Login, Register }
}