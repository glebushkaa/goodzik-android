package com.uni.fine.ui.screens.info

import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = InfoViewModel.Factory::class)
class InfoViewModel @AssistedInject constructor(
    @Assisted val id: String,
    @Assisted val new: Boolean,
    private val checkRepository: CheckRepository
) : StateViewModel<InfoState>(InfoState()) {

    init {
        collectCheck()
        if (!new) requestUpdate()
    }

    fun sendAction(action: InfoAction) {
        when (action) {
            InfoAction.HideIssue -> mutableState.update {
                it.copy(selectedIssue = null)
            }

            is InfoAction.IssueClicked -> showIssue(action.issueId)
        }
    }

    private fun requestUpdate() {
        launch {
            _loading.update { true }
            checkRepository.requestCheckUpdate(id)
            _loading.update { false }
        }
    }

    private fun collectCheck() {
        launch {
            checkRepository
                .getCheckWithIssuesById(id)
                .collect { check ->
                    mutableState.update {
                        it.copy(
                            issues = check.issues.toImmutableList(),
                            text = check.prompt
                        )
                    }
                }
        }
    }

    private fun showIssue(issueId: String) {
        val issue = state.value.issues.find { it.id == issueId } ?: return
        mutableState.update {
            it.copy(selectedIssue = issue)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String, new: Boolean): InfoViewModel
    }
}