package com.uni.goodzik.ui

import com.uni.goodzik.domain.SessionState
import com.uni.goodzik.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionState: SessionState
) : BaseViewModel() {

    private val _sideEffect = MutableSharedFlow<MainSideEffect>(extraBufferCapacity = 1)
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        collectSideEffect()
    }

    private fun collectSideEffect() {
        launch {
            sessionState.logOutAction.collect {
                _sideEffect.emit(MainSideEffect.LogOut)
            }
        }
    }
}