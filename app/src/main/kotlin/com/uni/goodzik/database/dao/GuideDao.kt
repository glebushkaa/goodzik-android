package com.uni.goodzik.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.uni.goodzik.database.entity.CommentEntity
import com.uni.goodzik.database.entity.GuideEntity
import com.uni.goodzik.database.entity.GuideImagesEntity
import com.uni.goodzik.database.entity.GuideWithComments
import com.uni.goodzik.database.entity.StepsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GuideDao {

    @Upsert
    suspend fun upsertGuides(guides: List<GuideEntity>)

    @Query("SELECT * FROM guide_entity")
    fun getGuidesFlow(): Flow<List<GuideEntity>>

    @Query("SELECT * FROM guide_entity WHERE id = :id")
    suspend fun getGuideById(id: String): GuideEntity?

    @Query("SELECT * FROM guide_entity WHERE id = :id")
    fun getGuideByIdFlow(id: String): Flow<GuideWithComments>

    @Upsert
    suspend fun upsertSteps(steps: List<StepsEntity>)

    @Query("SELECT * FROM steps_entity WHERE steps_entity.guide_id = :guideId")
    fun getStepsByGuideId(guideId: String): Flow<List<StepsEntity>>

    @Upsert
    suspend fun upsertComments(comment: List<CommentEntity>)

    @Upsert
    suspend fun upsertImages(images: List<GuideImagesEntity>)
}