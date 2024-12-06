package com.uni.goodzik.ui.screens.details

import com.uni.goodzik.domain.repository.GuideRepository
import com.uni.goodzik.model.Message
import com.uni.goodzik.ui.core.StateViewModel
import com.uni.goodzik.ui.core.component.ToastMessageData
import com.uni.goodzik.ui.core.extension.downloadFileToDownloads
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import okhttp3.internal.toImmutableList
import java.util.UUID

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val id: String,
    private val guideRepository: GuideRepository,
) : StateViewModel<DetailsState>(DetailsState()) {

    init {
        loadGuideDetails()
    }

    fun sendAction(action: DetailsAction) {
        when (action) {
            DetailsAction.Send -> sendComment()
            is DetailsAction.MessageUpdated -> mutableState.update {
                it.copy(messageText = action.message)
            }

            is DetailsAction.DownloadFile -> {
                launch(loadingEnabled = false) {
                    downloadFileToDownloads(action.url, "Scheme ${action.order} for $id")
                    showToast(
                        ToastMessageData("File downloaded", ToastMessageData.Type.Success)
                    )
                }
            }
        }
    }

    private fun sendComment() {
        launch {
            val message = mutableState.value.messageText
            guideRepository.sendComment(id, message)
            mutableState.update {
                it.copy(
                    comments = it.comments.toMutableList().apply {
                        add(
                            Message(
                                id = it.comments.lastOrNull()?.id + UUID.randomUUID().toString(),
                                text = message,
                                author = "You",
                                incoming = false
                            )
                        )
                    }.toImmutableList(),
                    messageText = ""
                )
            }
        }
    }

    private fun loadGuideDetails() {
        launch(loadingEnabled = false) {
            guideRepository.getGuideFlow(id).collectLatest { details ->
                mutableState.update {
                    it.copy(
                        title = details.title,
                        description = details.description,
                        author = details.author,
                        comments = details.comments.map {
                            Message(
                                id = it.id,
                                text = it.text,
                                author = it.author,
                                incoming = true
                            )
                        },
                        isStepsEmpty = details.,
                        exampleImages = details.images,
                        videoUrl = details.videoUrl
                    )
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): DetailsViewModel
    }
}