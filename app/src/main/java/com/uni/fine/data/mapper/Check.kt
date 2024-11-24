package com.uni.fine.data.mapper

import com.uni.fine.model.Check
import com.uni.fine.network.model.response.CheckInfoResponse
import com.uni.fine.ui.core.extension.toLocalDate

fun CheckInfoResponse.toDomain(): Check {
    return Check(
        id = id,
        title = title,
        summary = summary,
        createdAt = createdAt.toLocalDate()
    )
}