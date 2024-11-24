package com.uni.fine.data.repository

import com.uni.fine.database.dao.UserDao
import com.uni.fine.database.entity.UserEntity
import com.uni.fine.domain.repository.AuthRepository
import com.uni.fine.network.api.AuthApi
import com.uni.fine.network.model.request.LoginModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val userDao: UserDao
) : AuthRepository {

    override suspend fun login(email: String, password: String) {
        val request = LoginModel(email, password)
        val result = authApi.login(request)
        val user = UserEntity(token = result.accessToken ?: throw Exception("Token is null"))
        userDao.upsert(user)
    }

    override suspend fun register(email: String, password: String) {
        val request = LoginModel(email, password)
        val result = authApi.register(request)
        val user = UserEntity(token = result.accessToken ?: throw Exception("Token is null"))
        userDao.upsert(user)
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return userDao.getToken() != null
    }

    override suspend fun logOut() {
        userDao.delete()
    }
}