package com.uni.goodzik.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(email: String, password: String, name: String)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun logOut()
}