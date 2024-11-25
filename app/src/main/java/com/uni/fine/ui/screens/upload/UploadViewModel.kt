package com.uni.fine.ui.screens.upload

import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UploadViewModel @Inject constructor(
    private val checkRepository: CheckRepository
) : StateViewModel<UploadState>(UploadState()) {

    private val _sideEffect = MutableSharedFlow<UploadSideEffect>(extraBufferCapacity = 1)
    val sideEffect = _sideEffect.asSharedFlow()

    private var filePath: String? = null

    init {
        collectButtonReadyState()
    }

    fun sendAction(action: UploadAction) {
        when (action) {
            UploadAction.SendAction -> sendCheck()
            UploadAction.Clear -> clear()
            is UploadAction.TextChanged -> mutableState.update { it.copy(text = action.text) }
            is UploadAction.FilePicked -> {
                filePath = action.path
                mutableState.update { it.copy(fileName = action.fileName) }
            }
        }
    }

    private fun clear() {
        filePath = null
        mutableState.update {
            it.copy(fileName = "", text = "")
        }
    }

    private fun sendCheck() {
        launch {
            _loading.update { true }
            val id = checkRepository.completeCheck(
                path = filePath,
                text = state.value.text.ifBlank { null }
            )
            _loading.update { false }
            delay(300)
            _sideEffect.emit(UploadSideEffect.Next(id))
        }
    }

    private fun collectButtonReadyState() {
        launch {
            state.map {
                it.text.isNotEmpty() || filePath?.isNotEmpty() == true
            }.collectLatest { enabled ->
                mutableState.update {
                    it.copy(buttonEnabled = enabled)
                }
            }
        }
    }
}