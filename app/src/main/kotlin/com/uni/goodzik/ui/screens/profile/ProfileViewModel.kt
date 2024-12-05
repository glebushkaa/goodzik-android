package com.uni.goodzik.ui.screens.profile

import com.uni.goodzik.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : StateViewModel<ProfileState>(ProfileState()) {

    init {
        loadProfile()
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