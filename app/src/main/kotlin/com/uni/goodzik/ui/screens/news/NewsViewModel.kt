package com.uni.goodzik.ui.screens.news

import com.uni.goodzik.model.Category
import com.uni.goodzik.model.News
import com.uni.goodzik.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor() : StateViewModel<NewsState>(NewsState()) {

    init {
        loadNews()
    }

    private fun loadNews() {
        launch {
            val newsList = mutableListOf<News>()
            repeat(20) {
                val news = News(
                    id = it.toString(),
                    title = "Title",
                    description = "Learn how to sew linen with ease using our step-by-step guide. From selecting the right fabric to mastering essential" +
                            "Learn how to sew linen with ease using our step-by-step guide. From selecting the right fabric to mastering essential",
                    date = LocalDate.now(),
                    categories = listOf(Category("0", "Underwear"))
                )
                newsList.add(news)
            }
            mutableState.update {
                it.copy(news = newsList.toImmutableList())
            }
        }
    }
}