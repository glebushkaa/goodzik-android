package com.uni.goodzik.di

import com.uni.goodzik.data.SessionStateImpl
import com.uni.goodzik.domain.SessionState
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