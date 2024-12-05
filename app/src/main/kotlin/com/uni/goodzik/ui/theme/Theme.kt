package com.uni.goodzik.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun GoodzikTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides GoodzikTheme.colors,
        LocalTypography provides GoodzikTheme.typography,
        LocalPadding provides GoodzikTheme.padding,
        content = content
    )
}

object GoodzikTheme {
    val colors: GoodzikColor
        @Composable
        get() = LocalColors.current
    val typography: UniTypography
        @Composable
        get() = LocalTypography.current
    val padding: Padding
        @Composable
        get() = LocalPadding.current
}