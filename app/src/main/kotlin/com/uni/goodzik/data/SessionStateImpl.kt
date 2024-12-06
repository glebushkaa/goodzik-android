package com.uni.goodzik.data

import com.uni.goodzik.database.GuideDatabase
import com.uni.goodzik.domain.SessionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionStateImpl @Inject constructor(
    private val database: GuideDatabase
) : SessionState {

    private val _logOutAction = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    override val logOutAction = _logOutAction.asSharedFlow()

    override suspend fun logOut(): Unit = withContext(Dispatchers.IO) {
        database.clearAllTables()
        _logOutAction.tryEmit(Unit)
    }
}