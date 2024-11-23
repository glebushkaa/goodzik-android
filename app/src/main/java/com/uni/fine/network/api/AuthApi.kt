package com.uni.fine.network.api

import com.uni.fine.network.model.request.LoginModel
import com.uni.fine.network.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("signup")
    suspend fun register(@Body request: LoginModel): LoginResponse

    @POST("login")
    suspend fun login(@Body request: LoginModel): LoginResponse
}