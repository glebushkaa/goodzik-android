package com.uni.goodzik.ui.screens.steps

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.uni.goodzik.model.Step
import com.uni.goodzik.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = StepsViewModel.Factory::class)
class StepsViewModel @AssistedInject constructor(
    @Assisted private val id: String
) : StateViewModel<StepsState>(StepsState()) {

    init {
        loadSteps()
    }

    private fun loadSteps() {
        val persistentList = persistentListOf<Step>().builder()
        repeat(10) {
            val step = Step(
                id = it.toString(),
                order = it,
                guideTitle = "Guide title",
                name = "Title $it",
                description = LoremIpsum(100)
                    .values
                    .joinToString(),
                image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2024/02/attackontitan_anime_colossustitan_eren.jpg?q=50&fit=crop&w=943&h=&dpr=1.5"
            )
            persistentList.add(step)
        }
        mutableState.update {
            it.copy(steps = persistentList.build())
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): StepsViewModel
    }
}