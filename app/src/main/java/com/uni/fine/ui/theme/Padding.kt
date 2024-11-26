package com.uni.fine.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class Padding(
    val empty: Dp = 0.dp,
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val average: Dp = 12.dp,
    val large: Dp = 16.dp,
    val huge: Dp = 24.dp,
    val massive: Dp = 32.dp,
    val gigantic: Dp = 40.dp,
    val enormous: Dp = 48.dp,
    val colossal: Dp = 80.dp,
)

val LocalPadding = staticCompositionLocalOf { Padding() }