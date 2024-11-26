package com.uni.fine.data

import com.uni.fine.database.UniDatabase
import com.uni.fine.domain.SessionState
import com.uni.fine.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionStateImpl @Inject constructor(
    private val database: UniDatabase
) : SessionState {

    private val _logOutAction = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    override val logOutAction = _logOutAction.asSharedFlow()

    override suspend fun logOut(): Unit = withContext(Dispatchers.IO) {
        database.clearAllTables()
        _logOutAction.tryEmit(Unit)
    }
}