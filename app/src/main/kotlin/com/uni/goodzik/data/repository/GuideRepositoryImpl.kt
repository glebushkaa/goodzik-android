package com.uni.goodzik.data.repository

import com.uni.goodzik.data.mapper.toDomain
import com.uni.goodzik.data.mapper.toEntity
import com.uni.goodzik.database.dao.GuideDao
import com.uni.goodzik.database.entity.CommentEntity
import com.uni.goodzik.database.entity.GuideImagesEntity
import com.uni.goodzik.database.entity.StepsEntity
import com.uni.goodzik.domain.repository.GuideRepository
import com.uni.goodzik.model.Comment
import com.uni.goodzik.model.Guide
import com.uni.goodzik.model.GuideDetails
import com.uni.goodzik.model.Step
import com.uni.goodzik.network.api.GuideApi
import com.uni.goodzik.network.model.request.CommentRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class GuideRepositoryImpl @Inject constructor(
    private val guideApi: GuideApi,
    private val guideDao: GuideDao
) : GuideRepository {

    override suspend fun sendComment(id: String, message: String) {
        val request = CommentRequest(guideId = id, text = message)
        guideApi.sendComment(request)
    }

    override suspend fun getSteps(id: String): Flow<List<Step>> {
        val guide = guideDao.getGuideById(id)
        return guideDao.getStepsByGuideId(id).map { steps ->
            steps.map { step ->
                Step(
                    id = step.id,
                    description = step.description,
                    image = step.image,
                    order = step.order,
                    name = step.title,
                    guideTitle = guide?.title.orEmpty()
                )
            }
        }
    }

    override suspend fun getGuideFlow(id: String): Flow<GuideDetails> = supervisorScope {
        launch {
            val guideDetails = guideApi.getGuideDetails(id)
            val steps = guideDetails.steps.map { step ->
                StepsEntity(
                    id = step.id,
                    guideId = id,
                    title = step.name,
                    description = step.description,
                    image = step.image,
                    order = step.order
                )
            }
            val comments = guideDetails.comments.map { comment ->
                CommentEntity(
                    id = comment.id,
                    guideId = id,
                    text = comment.text,
                    author = comment.author
                )
            }
            val images = guideDetails.exampleImages.mapIndexed { index, image ->
                GuideImagesEntity(
                    guideId = id,
                    image = image,
                    id = id + index
                )
            }
            guideDao.upsertSteps(steps)
            guideDao.upsertImages(images)
            guideDao.upsertComments(comments)
        }
        return@supervisorScope guideDao.getGuideByIdFlow(id).map {
            GuideDetails(
                title = it.guide.title,
                description = it.guide.description,
                author = it.guide.author,
                videoUrl = it.guide.video,
                images = it.images.map { it.image },
                comments = it.comments.map { comment ->
                    Comment(
                        id = comment.id,
                        text = comment.text,
                        author = comment.author
                    )
                },
                steps = it.steps.map { step ->
                    Step(
                        id = step.id,
                        description = step.description,
                        image = step.image,
                        order = step.order,
                        name = step.title,
                        guideTitle = it.guide.title
                    )
                }
            )
        }
    }

    override suspend fun getGuidesFlow(): Flow<List<Guide>> = supervisorScope {
        launch {
            val result = guideApi.getGuides()
            val guides = result.map { it.toEntity() }
            guideDao.upsertGuides(guides)
        }
        return@supervisorScope guideDao.getGuidesFlow().map {
            it.map { guideEntity -> guideEntity.toDomain() }
        }
    }
}