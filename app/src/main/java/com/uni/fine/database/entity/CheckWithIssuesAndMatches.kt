package com.uni.fine.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CheckWithIssuesAndMatches(
    @Embedded
    val check: CheckEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkId"
    )
    val issues: List<IssueEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "checkId"
    )
    val matches: List<MatchEntity>
)