package com.uni.goodzik.ui.screens.profile

import com.uni.goodzik.domain.SessionState
import com.uni.goodzik.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sessionState: SessionState
) : StateViewModel<ProfileState>(ProfileState()) {

    init {
        loadProfile()
    }

    fun sendAction(action: ProfileAction) {
        when (action) {
            ProfileAction.LogOut -> logOut()
        }
    }

    private fun logOut() {
        launch { sessionState.logOut() }
    }

    private fun loadProfile() {
        mutableState.update {
            it.copy(
                username = "Goodzik",
                email = "goodzik@mail.com"
            )
        }
    }
}