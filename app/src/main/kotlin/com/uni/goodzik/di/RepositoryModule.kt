package com.uni.goodzik.di

import com.uni.goodzik.data.repository.AuthRepositoryImpl
import com.uni.goodzik.data.repository.GuideRepositoryImpl
import com.uni.goodzik.data.repository.NewsRepositoryImpl
import com.uni.goodzik.domain.repository.AuthRepository
import com.uni.goodzik.domain.repository.GuideRepository
import com.uni.goodzik.domain.repository.NewsRepository
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
    fun bindGuideRepository(guideRepositoryImpl: GuideRepositoryImpl): GuideRepository

    @Binds
    @Singleton
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}