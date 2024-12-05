package com.uni.goodzik.ui.screens.news_details

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.uni.goodzik.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = NewsDetailsViewModel.Factory::class)
class NewsDetailsViewModel @AssistedInject constructor(
    @Assisted private val id: String
) : StateViewModel<NewsDetailsState>(NewsDetailsState()) {

    init {
        loadDetails()
    }

    private fun loadDetails() {
        val list = buildList {
            repeat(5) {
                add("https://static0.gamerantimages.com/wordpress/wp-content/uploads/2024/02/attackontitan_anime_colossustitan_eren.jpg?q=50&fit=crop&w=943&h=&dpr=1.5")
            }
        }
        mutableState.update {
            it.copy(
                title = "Title $id",
                author = "Author with $id",
                description = LoremIpsum(100).values.joinToString(),
                images = list.toImmutableList()
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): NewsDetailsViewModel
    }
}