package com.uni.goodzik.ui.screens.steps

import com.uni.goodzik.domain.repository.GuideRepository
import com.uni.goodzik.ui.core.StateViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = StepsViewModel.Factory::class)
class StepsViewModel @AssistedInject constructor(
    @Assisted private val id: String,
    private val guideRepository: GuideRepository,
) : StateViewModel<StepsState>(StepsState()) {

    init {
        loadSteps()
    }

    private fun loadSteps() {
        launch(loadingEnabled = false) {
            guideRepository.getSteps(id).collectLatest { steps ->
                mutableState.update {
                    it.copy(steps = steps.toImmutableList())
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: String): StepsViewModel
    }
}