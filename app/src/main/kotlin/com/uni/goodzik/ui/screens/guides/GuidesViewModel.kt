package com.uni.goodzik.ui.screens.guides

import com.uni.goodzik.model.Category
import com.uni.goodzik.model.Guide
import com.uni.goodzik.ui.core.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class GuidesViewModel @Inject constructor() : StateViewModel<GuidesState>(GuidesState()) {

    init {
        loadGuides()
    }

    private fun loadGuides() {
        launch {
            val guides = mutableListOf<Guide>()
            repeat(20) {
                val guide = Guide(
                    id = it.toString(),
                    title = "Title",
                    description = "Learn how to sew linen with ease using our step-by-step guide. From selecting the right fabric to mastering essential" +
                            "Learn how to sew linen with ease using our step-by-step guide. From selecting the right fabric to mastering essential",
                    date = LocalDate.now(),
                    categories = listOf(Category("0", "Underwear"))
                )
                guides.add(guide)
            }
            mutableState.update {
                it.copy(guides = guides.toImmutableList())
            }
        }
    }
}