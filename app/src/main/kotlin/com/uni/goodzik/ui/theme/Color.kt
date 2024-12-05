package com.uni.goodzik.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class UniColor(
    val amber: Color = Color(0xFFFFB350),
    val black: Color = Color(0xFF000000),
    val white: Color = Color(0xFFFFFFFF),
    val ocean: Color = Color(0xFF5686FF),
    val gray: Color = Color(0xFF666666),
    val red: Color = Color(0xFFFF7676),
    val green: Color = Color(0xFFA2C796),
)

val LocalColors = staticCompositionLocalOf { UniColor() }

