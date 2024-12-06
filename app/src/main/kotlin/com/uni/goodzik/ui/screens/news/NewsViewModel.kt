package com.uni.goodzik.ui.screens.news

import com.uni.goodzik.domain.repository.NewsRepository
import com.uni.goodzik.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : StateViewModel<NewsState>(NewsState()) {

    init {
        collectNews()
    }

    private fun collectNews() {
        launch(loadingEnabled = false) {
            newsRepository.getNews().collectLatest { news ->
                mutableState.update {
                    it.copy(news = news.toImmutableList())
                }
            }
        }
    }
}