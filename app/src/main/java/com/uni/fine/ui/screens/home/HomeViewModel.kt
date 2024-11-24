package com.uni.fine.ui.screens.home

import com.uni.fine.domain.SessionState
import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val checkRepository: CheckRepository,
    private val sessionState: SessionState
) : StateViewModel<HomeState>(HomeState()) {

    init {
        loadChecks()
    }

    fun sendAction(action: HomeAction) {
        when (action) {
            is HomeAction.CheckClicked -> TODO()
            HomeAction.CreateCheckClicked -> TODO()
            HomeAction.LogOutClicked -> logOut()
        }
    }

    private fun logOut() {
        launch { sessionState.logOut() }
    }

    private fun loadChecks() {
        launch {
            val result = checkRepository.getChecks().toImmutableList()
            mutableState.update {
                it.copy(checks = result)
            }
        }
    }
}