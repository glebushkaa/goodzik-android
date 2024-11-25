package com.uni.fine.data.repository

import android.content.Context
import androidx.core.net.toUri
import com.uni.fine.data.mapper.toDomain
import com.uni.fine.data.mapper.toEntity
import com.uni.fine.database.dao.CheckDao
import com.uni.fine.database.entity.CheckEntity
import com.uni.fine.database.entity.CheckWithIssues
import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.model.Check
import com.uni.fine.model.CheckInfo
import com.uni.fine.model.CheckSetup
import com.uni.fine.model.CheckStyle
import com.uni.fine.network.api.CheckApi
import com.uni.fine.network.model.response.CheckInfoResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class CheckRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val checkApi: CheckApi,
    private val checkDao: CheckDao
) : CheckRepository {

    private var recentCheckSetup: CheckSetup? = null

    override suspend fun requestCheckUpdate(id: String) {
        val result = checkApi.getCheck(id)
        val check = result.toEntity()
        val issues = result.issues.map { it.toEntity(check.id) }
        checkDao.upsertCheck(check, issues)
    }

    override suspend fun getCheckWithIssuesById(id: String): Flow<CheckInfo> {
        return checkDao.getCheckWithIssuesById(id).map(CheckWithIssues::toDomain)
    }

    override suspend fun requestChecksUpdate() {
        val result = checkApi.getChecks()
        val checks = result.map(CheckInfoResponse::toEntity)
        checkDao.upsertChecks(checks)
    }

    override suspend fun getChecks(): Flow<List<Check>> {
        return checkDao.getChecks().map {
            it.map(CheckEntity::toDomain)
        }
    }

    override suspend fun setupCheck(topic: String, style: CheckStyle, excludedWords: String) {
        recentCheckSetup = CheckSetup(topic = topic, style = style, excludedWords = excludedWords)
    }

    override suspend fun completeCheck(
        path: String?,
        text: String?
    ): String {
        val file = path?.let { path ->
            val uri = path.toUri()
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                val fileName = uri.lastPathSegment ?: return@let null
                val tempFile = File(context.cacheDir, fileName)
                tempFile.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
                tempFile.takeIf { it.exists() }
            }
        }
        val result = checkApi.createCheck(
            file = file?.let {
                MultipartBody.Part.createFormData(
                    name = "file",
                    filename = it.name,
                    body = it.asRequestBody()
                )
            },
            prompt = text?.toRequestBody(),
            topic = (recentCheckSetup?.topic ?: "").toRequestBody(),
            excludedWords = (recentCheckSetup?.excludedWords ?: "").toRequestBody(),
            style = (recentCheckSetup?.style?.name ?: "").toRequestBody()
        )
        val check = result.toEntity()
        val issues = result.issues.map { it.toEntity(check.id) }
        checkDao.upsertCheck(check, issues)
        return result.id
    }
}