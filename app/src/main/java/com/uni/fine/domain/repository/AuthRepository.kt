package com.uni.fine.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(email: String, password: String)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun logOut()
}