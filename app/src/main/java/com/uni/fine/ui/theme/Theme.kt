package com.uni.fine.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun UniFineTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides UniFineTheme.colors,
        LocalTypography provides UniFineTheme.typography,
        LocalPadding provides UniFineTheme.padding,
        content = content
    )
}

object UniFineTheme {
    val colors: UniColor
        @Composable
        get() = LocalColors.current
    val typography: UniTypography
        @Composable
        get() = LocalTypography.current
    val padding: Padding
        @Composable
        get() = LocalPadding.current
}