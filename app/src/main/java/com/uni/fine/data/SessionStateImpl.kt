package com.uni.fine.data

import com.uni.fine.domain.SessionState
import com.uni.fine.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class SessionStateImpl @Inject constructor(
    private val authRepository: AuthRepository
) : SessionState {

    private val _logOutAction = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    override val logOutAction = _logOutAction.asSharedFlow()

    override suspend fun logOut() {
        authRepository.logOut()
        _logOutAction.tryEmit(Unit)
    }
}