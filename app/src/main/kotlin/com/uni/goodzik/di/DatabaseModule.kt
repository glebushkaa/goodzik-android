package com.uni.goodzik.di

import android.content.Context
import androidx.room.Room
import com.uni.goodzik.database.GuideDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): GuideDatabase = Room.databaseBuilder(
        context = context,
        klass = GuideDatabase::class.java,
        name = "goodzik_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(database: GuideDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideGuideDao(database: GuideDatabase) = database.guideDao()

    @Provides
    @Singleton
    fun provideNewsDao(database: GuideDatabase) = database.newsDao()
}