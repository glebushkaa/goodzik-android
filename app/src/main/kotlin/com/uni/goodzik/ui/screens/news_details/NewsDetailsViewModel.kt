package com.uni.goodzik.ui.screens.news_details

import com.uni.goodzik.domain.repository.NewsRepository
import com.uni.goodzik.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = NewsDetailsViewModel.Factory::class)
class NewsDetailsViewModel @AssistedInject constructor(
    @Assisted private val id: String,
    private val newsRepository: NewsRepository,
) : StateViewModel<NewsDetailsState>(NewsDetailsState()) {

    init {
        loadDetails()
    }

    private fun loadDetails() {
        launch {
            val news = newsRepository.getNewsWithImage(id) ?: return@launch
            mutableState.update {
                it.copy(
                    title = news.title,
                    description = news.description,
                    images = news.images.toImmutableList(),
                    author = news.author,
                )
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): NewsDetailsViewModel
    }
}