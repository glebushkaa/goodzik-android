package com.uni.goodzik.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class GuideWithComments(
    @Embedded
    val guide: GuideEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "guide_id"
    )
    val comments: List<CommentEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "guide_id"
    )
    val images: List<GuideImagesEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "guide_id"
    )
    val steps: List<StepsEntity>
)