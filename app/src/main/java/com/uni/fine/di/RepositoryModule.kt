package com.uni.fine.di

import com.uni.fine.data.repository.AuthRepositoryImpl
import com.uni.fine.data.repository.CheckRepositoryImpl
import com.uni.fine.domain.repository.AuthRepository
import com.uni.fine.domain.repository.CheckRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindCheckRepository(checkRepositoryImpl: CheckRepositoryImpl): CheckRepository
}