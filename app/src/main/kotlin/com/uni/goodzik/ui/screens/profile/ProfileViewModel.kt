package com.uni.goodzik.ui.screens.profile

import com.uni.goodzik.domain.SessionState
import com.uni.goodzik.domain.repository.AuthRepository
import com.uni.goodzik.domain.repository.NewsRepository
import com.uni.goodzik.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionState: SessionState,
    private val authRepository: AuthRepository
) : StateViewModel<ProfileState>(ProfileState()) {

    init {
        loadProfile()
        collectProfile()
    }

    fun sendAction(action: ProfileAction) {
        when (action) {
            ProfileAction.LogOut -> logOut()
        }
    }

    private fun logOut() {
        launch { sessionState.logOut() }
    }

    private fun collectProfile() {
        launch(loadingEnabled = false) {
            authRepository.profile.collectLatest { profile ->
                mutableState.update { state ->
                    state.copy(
                        username = profile.username,
                        email = profile.email
                    )
                }
            }
        }
    }

    private fun loadProfile() {
        launch(loadingEnabled = false) {
            authRepository.requestUpdateProfile()
        }
    }
}