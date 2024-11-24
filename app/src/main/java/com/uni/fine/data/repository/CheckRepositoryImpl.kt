package com.uni.fine.data.repository

import com.uni.fine.data.mapper.toDomain
import com.uni.fine.domain.repository.CheckRepository
import com.uni.fine.model.Check
import com.uni.fine.network.api.CheckApi
import com.uni.fine.network.model.response.CheckInfoResponse
import javax.inject.Inject

class CheckRepositoryImpl @Inject constructor(
    private val checkApi: CheckApi
) : CheckRepository {

    override suspend fun getChecks(): List<Check> {
        return checkApi.getChecks().map(CheckInfoResponse::toDomain)
    }
}