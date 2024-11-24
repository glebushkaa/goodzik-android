package com.uni.fine.network.api

import com.uni.fine.network.model.response.CheckInfoResponse
import retrofit2.http.GET

interface CheckApi {

    @GET("checks")
    suspend fun getChecks(): List<CheckInfoResponse>
}