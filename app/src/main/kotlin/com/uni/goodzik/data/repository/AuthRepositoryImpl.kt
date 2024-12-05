package com.uni.goodzik.data.repository

import com.uni.goodzik.database.dao.UserDao
import com.uni.goodzik.database.entity.UserEntity
import com.uni.goodzik.domain.repository.AuthRepository
import com.uni.goodzik.network.api.AuthApi
import com.uni.goodzik.network.model.request.LoginModel
import com.uni.goodzik.network.model.request.RegisterModel
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

    override suspend fun register(email: String, password: String, name: String) {
        val request = RegisterModel(
            email = email,
            password = password,
            username = name
        )
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