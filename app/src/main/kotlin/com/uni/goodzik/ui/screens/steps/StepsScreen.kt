package com.uni.goodzik.ui.screens.steps

import androidx.compose.runtime.Composable
import com.uni.goodzik.ui.core.component.Screen

@Composable
fun StepsScreen(id: String) {
    Screen<StepsViewModel, StepsViewModel.Factory>(
        creationCallback = { factory -> factory.create(id) }
    ) { viewModel ->

    }
}

@Composable
private fun StepsScreenContent(state: StepsState) {

}