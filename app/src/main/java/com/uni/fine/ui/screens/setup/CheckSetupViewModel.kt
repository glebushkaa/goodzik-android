package com.uni.fine.ui.screens.setup

import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.model.CheckStyle
import com.uni.fine.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CheckSetupViewModel @Inject constructor(
    private val checkRepository: CheckRepository
) : StateViewModel<CheckSetupState>(CheckSetupState()) {

    private val _sideEffect = MutableSharedFlow<CheckSetupSideEffect>(extraBufferCapacity = 1)
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        collectNextButtonState()
    }

    fun sendAction(action: CheckSetupAction) {
        when (action) {
            is CheckSetupAction.TopicChanged -> onTopicChanged(action.topic)
            is CheckSetupAction.StyleChanged -> onStyleChanged(action.style)
            is CheckSetupAction.ExcludedWordsChanged -> onExcludedWordsChanged(action.excludedWords)
            is CheckSetupAction.Next -> next()
        }
    }

    private fun onTopicChanged(topic: String) {
        mutableState.update { it.copy(topic = topic) }
    }

    private fun onStyleChanged(style: CheckStyle) {
        mutableState.update { it.copy(style = style) }
    }

    private fun onExcludedWordsChanged(excludedWords: String) {
        mutableState.update { it.copy(excludedWords = excludedWords) }
    }

    private fun next() {
        launch {
            checkRepository.setupCheck(
                topic = state.value.topic,
                style = state.value.style,
                excludedWords = state.value.excludedWords
            )
            _sideEffect.emit(CheckSetupSideEffect.Next)
        }
    }

    private fun collectNextButtonState() {
        launch {
            state.map { it.topic.isNotBlank() }
                .distinctUntilChanged()
                .collect { ready ->
                    mutableState.update { it.copy(buttonEnabled = ready) }
                }
        }
    }
}