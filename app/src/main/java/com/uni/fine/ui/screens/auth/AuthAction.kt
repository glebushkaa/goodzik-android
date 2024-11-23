package com.uni.fine.ui.screens.auth

sealed interface AuthAction {
    data object CreateAccount : AuthAction
    data object CreateAccountMode : AuthAction
    data class UpdatePassword(val password: String) : AuthAction
    data class UpdateEmail(val email: String) : AuthAction
    data class UpdateConfirmPassword(val confirmPassword: String) : AuthAction
    data object Login : AuthAction
    data object Back : AuthAction
}