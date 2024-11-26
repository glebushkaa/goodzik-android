package com.uni.fine.ui.screens.info

import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.model.CheckInfo
import com.uni.fine.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = InfoViewModel.Factory::class)
class InfoViewModel @AssistedInject constructor(
    @Assisted val id: String,
    @Assisted val new: Boolean,
    private val checkRepository: CheckRepository
) : StateViewModel<InfoState>(InfoState()) {

    private var isUpdated = false

    init {
        collectCheck()
    }

    fun sendAction(action: InfoAction) {
        when (action) {
            InfoAction.HideIssue -> mutableState.update {
                it.copy(selectedIssue = null, sheet = null)
            }

            is InfoAction.IssueClicked -> showIssue(action.issueId)
            InfoAction.RequestMatches -> requestMatches()
        }
    }

    private fun requestUpdate() {
        launch {
            _loading.update { true }
            checkRepository.requestCheckUpdate(id)
            _loading.update { false }
        }
    }

    private fun requestMatches() {
        launch(
            onError = {
                mutableState.update {
                    it.copy(
                        buttonVisible = false,
                        buttonLoading = false
                    )
                }
            }
        ) {
            if (state.value.plagiarisms.isNotEmpty()) {
                mutableState.update { it.copy(sheet = Sheet.Plagiarism) }
                return@launch
            }
            mutableState.update { it.copy(buttonLoading = true) }
            val result = checkRepository.requestMatchesUpdate(id)
            mutableState.update {
                it.copy(
                    buttonLoading = false,
                    buttonVisible = result,
                    sheet = if (result) Sheet.Plagiarism else it.sheet
                )
            }
        }
    }

    private fun collectCheck() {
        launch {
            checkRepository
                .getCheckWithIssuesById(id)
                .collect { check ->
                    if (!isUpdated && (check.prompt.isBlank() || new)) {
                        requestUpdate()
                        isUpdated = true
                    }
                    mutableState.update {
                        it.copy(
                            issues = check.issues.toImmutableIssues(check.prompt),
                            text = check.prompt,
                            aiScore = check.aiScore,
                            plagiarisms = check.plagiarism
                                .sortedByDescending { it.probability }
                                .toImmutableList()
                        )
                    }
                }
        }
    }

    private fun List<CheckInfo.Issue>.toImmutableIssues(text: String): ImmutableList<CheckInfo.Issue> {
        val innerText = text.lowercase().replace("\"", "\'")
        return mapNotNull {
            val startIndex = innerText.indexOf(it.text.lowercase())
            if (startIndex == -1) return@mapNotNull null
            val endIndex = startIndex + it.text.length
            it.copy(start = startIndex, end = endIndex)
        }.toImmutableList()
    }

    private fun showIssue(issueId: String) {
        val issue = state.value.issues.find { it.id == issueId } ?: return
        mutableState.update {
            it.copy(
                selectedIssue = issue,
                sheet = Sheet.Issue
            )
        }
    }

    enum class Sheet {
        Issue, Plagiarism
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String, new: Boolean): InfoViewModel
    }
}