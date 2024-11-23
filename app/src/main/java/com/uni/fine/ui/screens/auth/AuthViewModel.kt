package com.uni.fine.ui.screens.auth

import com.uni.fine.domain.repository.AuthRepository
import com.uni.fine.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : StateViewModel<AuthState>(AuthState()) {

    private val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex()
    private val passwordRegex =
        "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{8,}$".toRegex()

    private val _sideEffect = MutableSharedFlow<AuthSideEffect>(extraBufferCapacity = 1)
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        collectReadyState()
    }

    fun sendAction(action: AuthAction) {
        when (action) {
            is AuthAction.CreateAccountMode -> mutableState.update {
                it.copy(type = AuthState.AuthType.Register)
            }

            is AuthAction.UpdateConfirmPassword -> mutableState.update {
                it.copy(confirmPassword = action.confirmPassword.trim())
            }

            is AuthAction.UpdateEmail -> mutableState.update {
                it.copy(email = action.email.trim())
            }

            is AuthAction.UpdatePassword -> mutableState.update {
                it.copy(password = action.password.trim())
            }

            AuthAction.Back -> back()
            AuthAction.Login -> login()
            AuthAction.CreateAccount -> createAccount()
        }
    }

    private fun back() {
        if (state.value.type == AuthState.AuthType.Login) {
            _sideEffect.tryEmit(AuthSideEffect.Back)
            return
        }
        mutableState.update {
            it.copy(type = AuthState.AuthType.Login)
        }
    }

    private fun login() {
        launch {
            authRepository.login(
                email = state.value.email,
                password = state.value.password
            )
        }
    }

    private fun createAccount() {
        launch {
            authRepository.register(
                email = state.value.email,
                password = state.value.password
            )
        }
    }

    private fun collectReadyState() {
        launch {
            state.collectLatest { state ->
                val isEmailValid = state.email.matches(emailRegex)
                val isPasswordValid = state.password.matches(passwordRegex)
                val isConfirmPasswordValid =
                    (state.type == AuthState.AuthType.Login || state.confirmPassword == state.password)
                val condition = isEmailValid &&
                        isPasswordValid &&
                        isConfirmPasswordValid
                mutableState.update {
                    it.copy(buttonEnabled = condition)
                }
            }
        }
    }
}