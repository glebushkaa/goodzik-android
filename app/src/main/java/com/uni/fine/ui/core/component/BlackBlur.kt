package com.uni.fine.ui.core.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.uni.fine.ui.core.extension.applyIf
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.theme.UniFineTheme

@Composable
fun BlackBlur(
    blurState: BlurState,
    modifier: Modifier = Modifier,
) {
    val boxAlpha by animateFloatAsState(targetValue = if (blurState.enabled) 1f else 0f, label = "")

    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                alpha = boxAlpha
            }
            .background(UniFineTheme.colors.black.copy(alpha = 0.7f))
            .applyIf(blurState.enabled) {
                clickableNoRipple(onClick = blurState::hide)
            }
            .systemBarsPadding()
    )
}

class BlurState {

    var enabled by mutableStateOf(false)
        private set

    fun show() {
        enabled = true
    }

    fun hide() {
        enabled = false
    }

    companion object {
        private const val ENABLED_KEY = "enabled"
        val saver = mapSaver(
            save = {
                mapOf(ENABLED_KEY to it.enabled)
            },
            restore = {
                BlurState().apply {
                    enabled = it.getOrDefault(ENABLED_KEY, false) as Boolean
                }
            }
        )
    }
}

@Composable
fun rememberBlurState(): BlurState = rememberSaveable(saver = BlurState.saver) {
    BlurState()
}