package com.uni.fine.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CheckWithIssues(
    @Embedded
    val check: CheckEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkId"
    )
    val issues: List<IssueEntity>
)