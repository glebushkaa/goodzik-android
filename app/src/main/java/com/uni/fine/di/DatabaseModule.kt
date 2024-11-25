package com.uni.fine.di

import android.content.Context
import androidx.room.Room
import com.uni.fine.database.UniDatabase
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
    ): UniDatabase = Room.databaseBuilder(
        context = context,
        klass = UniDatabase::class.java,
        name = "uni_database"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(database: UniDatabase) = database.userDao()

    @Provides
    @Singleton
    fun provideCheckDao(database: UniDatabase) = database.checkDao()
}