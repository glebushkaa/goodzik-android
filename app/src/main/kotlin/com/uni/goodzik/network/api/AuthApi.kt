package com.uni.goodzik.network.api

import com.uni.goodzik.network.model.request.LoginModel
import com.uni.goodzik.network.model.request.RegisterModel
import com.uni.goodzik.network.model.response.LoginResponse
import com.uni.goodzik.network.model.response.ProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("signup")
    suspend fun register(@Body request: RegisterModel): LoginResponse

    @POST("login")
    suspend fun login(@Body request: LoginModel): LoginResponse

    @GET("me")
    suspend fun getProfile(): ProfileResponse
}