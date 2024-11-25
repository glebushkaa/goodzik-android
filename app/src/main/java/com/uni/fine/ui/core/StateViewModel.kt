package com.uni.fine.ui.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {
    protected val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend () -> Unit
    ): Job = viewModelScope.launch(context = context) {
        try {
            block()
        } catch (e: Exception) {
            Log.e("BaseViewModel", "launch: ${e.message}")
        }
    }
}

abstract class StateViewModel<T>(
    initialState: T
) : BaseViewModel() {
    protected val mutableState = MutableStateFlow(initialState)
    val state = mutableState.asStateFlow()
}