package com.uni.goodzik.ui.screens.guides

import com.uni.goodzik.domain.repository.GuideRepository
import com.uni.goodzik.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GuidesViewModel @Inject constructor(
    private val guideRepository: GuideRepository
) : StateViewModel<GuidesState>(GuidesState()) {

    init {
        collectGuides()
    }

    private fun collectGuides() {
        launch(loadingEnabled = false) {
            guideRepository.getGuidesFlow().collectLatest { guides ->
                mutableState.update { state ->
                    state.copy(guides = guides.toImmutableList())
                }
            }
        }
    }
}