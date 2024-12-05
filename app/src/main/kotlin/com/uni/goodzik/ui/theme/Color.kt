package com.uni.goodzik.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class GoodzikColor(
    val amber: Color = Color(0xFFFFB350),
    val black: Color = Color(0xFF000000),
    val snow: Color = Color(0xFFFFFFFF),
    val milk: Color = Color(0xFFF9F9F9),
    val ocean: Color = Color(0xFF5686FF),
    val gray: Color = Color(0xFF666666),
    val description: Color = Color(0xFF7D82A1),
    val red: Color = Color(0xFFFF7676),
    val green: Color = Color(0xFF1FC16B),
    val purple: Color = Color(0xFFBC7AF2),
    val disabled: Color = Color(0xFFB9B9B9)
)

val LocalColors = staticCompositionLocalOf { GoodzikColor() }

