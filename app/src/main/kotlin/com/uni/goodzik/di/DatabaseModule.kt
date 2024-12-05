package com.uni.goodzik.di

import android.content.Context
import androidx.room.Room
import com.uni.goodzik.database.SetupDatabase
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
    ): SetupDatabase = Room.databaseBuilder(
        context = context,
        klass = SetupDatabase::class.java,
        name = "uni_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(database: SetupDatabase) = database.userDao()
}