package com.uni.goodzik.ui.screens.splash

import com.uni.goodzik.domain.repository.AuthRepository
import com.uni.goodzik.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val _sideEffect = MutableSharedFlow<SplashSideEffect>(extraBufferCapacity = 1)
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        launch {
           delay(2000)
            /*val isLoggedIn = authRepository.isUserLoggedIn()
            _sideEffect.tryEmit(
                if (isLoggedIn) SplashSideEffect.NavigateToHome else SplashSideEffect.NavigateToAuth
            )*/
            _sideEffect.tryEmit(SplashSideEffect.NavigateToHome)
        }
    }
}