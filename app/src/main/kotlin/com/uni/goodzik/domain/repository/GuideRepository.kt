package com.uni.goodzik.domain.repository

import com.uni.goodzik.model.Guide
import com.uni.goodzik.model.GuideDetails
import com.uni.goodzik.model.Step
import kotlinx.coroutines.flow.Flow

interface GuideRepository {

    suspend fun getGuidesFlow(): Flow<List<Guide>>
    suspend fun getGuideFlow(id: String): Flow<GuideDetails>
    suspend fun getSteps(id: String): Flow<List<Step>>

    suspend fun sendComment(id: String, message: String)
}