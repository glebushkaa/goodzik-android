package com.uni.fine.di

import com.uni.fine.database.dao.UserDao
import com.uni.fine.domain.SessionState
import com.uni.fine.network.api.AuthApi
import com.uni.fine.network.api.CheckApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val GLOBAL_TIMEOUT = Duration.ofSeconds(120)
    private const val BASE_URL = "https://unifine.eventbook.pp.ua/"

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            explicitNulls = false
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    fun provideConverterFactory(json: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return json.asConverterFactory(contentType)
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        userDao: UserDao,
        sessionState: SessionState
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(buildLoggingInterceptor())
        .addInterceptor(buildAuthInterceptor(userDao))
        .addInterceptor(buildSessionInterceptor(sessionState))
        .setTimeouts()
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create()

    @Provides
    @Singleton
    fun provideCheckApi(retrofit: Retrofit): CheckApi = retrofit.create()

    private fun buildAuthInterceptor(userDao: UserDao): Interceptor = Interceptor { chain ->
        val token = runBlocking { userDao.getToken() }
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(newRequest)
    }

    private fun buildLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun buildSessionInterceptor(sessionState: SessionState): Interceptor = Interceptor {
        val response = it.proceed(it.request())
        if (response.code == 401) {
            runBlocking { sessionState.logOut() }
        }
        response
    }

    private fun OkHttpClient.Builder.setTimeouts(): OkHttpClient.Builder {
        return connectTimeout(GLOBAL_TIMEOUT)
            .writeTimeout(GLOBAL_TIMEOUT)
            .readTimeout(GLOBAL_TIMEOUT)
    }
}