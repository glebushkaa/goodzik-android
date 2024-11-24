package com.uni.fine.ui.core.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.clickableNoRipple(
    onClick: () -> Unit,
) = clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick
)

@Composable
fun Modifier.applyIf(
    condition: Boolean,
    action: @Composable Modifier.() -> Modifier
): Modifier = if (condition) this.then(action()) else this