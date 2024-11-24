package com.uni.fine.domain.repository

import com.uni.fine.model.Check

interface CheckRepository {

    suspend fun getChecks(): List<Check>
}