package com.uni.goodzik.ui.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uni.goodzik.ui.core.component.ToastMessageData
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {
    protected val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _toast = Channel<ToastMessageData>()
    val toast = _toast.receiveAsFlow()

    protected fun showToast(data: ToastMessageData) {
        _toast.trySend(data)
    }

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        onError: (e: Exception) -> Unit = {},
        block: suspend () -> Unit,
    ): Job = viewModelScope.launch(context = context) {
        try {
            block()
        } catch (e: Exception) {
            onError(e)
            showToast(
                ToastMessageData(
                    text = "Something went wrong",
                    type = ToastMessageData.Type.Error
                )
            )
            Log.e("BaseViewModel", "launch: ${e.message}")
        } finally {
            _loading.update { false }
        }
    }
}

abstract class StateViewModel<T>(
    initialState: T
) : BaseViewModel() {
    protected val mutableState = MutableStateFlow(initialState)
    val state = mutableState.asStateFlow()
}