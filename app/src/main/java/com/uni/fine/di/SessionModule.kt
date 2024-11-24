package com.uni.fine.di

import com.uni.fine.data.SessionStateImpl
import com.uni.fine.domain.SessionState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SessionModule {

    @Binds
    @Singleton
    fun bindSessionState(sessionState: SessionStateImpl): SessionState
}