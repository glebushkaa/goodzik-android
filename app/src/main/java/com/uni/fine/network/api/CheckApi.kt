package com.uni.fine.network.api

import com.uni.fine.network.model.request.MatchesRequest
import com.uni.fine.network.model.response.CheckInfoResponse
import com.uni.fine.network.model.response.CreatedCheckResponse
import com.uni.fine.network.model.response.MatchResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface CheckApi {

    @GET("checks")
    suspend fun getChecks(): List<CheckInfoResponse>

    @GET("checks/{id}")
    suspend fun getCheck(
        @Path("id") id: String
    ): CreatedCheckResponse

    @Multipart
    @POST("checks")
    suspend fun createCheck(
        @Part file: MultipartBody.Part? = null,
        @Part("prompt") prompt: RequestBody? = null,
        @Part("topic") topic: RequestBody,
        @Part("excludedWords") excludedWords: RequestBody,
        @Part("style") style: RequestBody
    ): CreatedCheckResponse

    @POST("checks/{id}/matches")
    suspend fun requestMatches(
        @Path("id") id: String,
        @Body request: MatchesRequest
    ): List<MatchResponse>
}