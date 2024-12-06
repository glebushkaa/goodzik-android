package com.uni.goodzik.network.api

import com.uni.goodzik.network.model.request.CommentRequest
import com.uni.goodzik.network.model.response.GuideDetailsResponse
import com.uni.goodzik.network.model.response.GuideResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GuideApi {

    @GET("guides")
    suspend fun getGuides(): List<GuideResponse>

    @GET("guides/{id}")
    suspend fun getGuideDetails(
        @Path("id") id: String
    ): GuideDetailsResponse

    @POST("guide-comments")
    suspend fun sendComment(@Body request: CommentRequest)
}