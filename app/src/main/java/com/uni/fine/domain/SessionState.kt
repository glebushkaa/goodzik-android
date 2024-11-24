package com.uni.fine.domain

import kotlinx.coroutines.flow.SharedFlow

interface SessionState {

    val logOutAction: SharedFlow<Unit>

    suspend fun logOut()
}