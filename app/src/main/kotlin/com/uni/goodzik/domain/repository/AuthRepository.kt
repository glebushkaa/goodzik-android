package com.uni.goodzik.domain.repository

import com.uni.goodzik.model.Profile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val profile: Flow<Profile>

    suspend fun requestUpdateProfile()

    suspend fun login(email: String, password: String)
    suspend fun register(email: String, password: String, name: String)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun logOut()
}