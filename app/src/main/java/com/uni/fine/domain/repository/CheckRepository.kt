package com.uni.fine.domain.repository

import com.uni.fine.model.Check
import com.uni.fine.model.CheckInfo
import com.uni.fine.model.CheckStyle
import kotlinx.coroutines.flow.Flow

interface CheckRepository {

    suspend fun requestCheckUpdate(id: String)
    suspend fun getCheckWithIssuesById(id: String): Flow<CheckInfo>
    suspend fun requestChecksUpdate()
    suspend fun getChecks(): Flow<List<Check>>
    suspend fun setupCheck(topic: String, style: CheckStyle, excludedWords: String)
    suspend fun completeCheck(path: String? = null, text: String? = null): String
}