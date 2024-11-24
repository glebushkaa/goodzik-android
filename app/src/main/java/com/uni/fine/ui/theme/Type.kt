package com.uni.fine.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.uni.fine.R

private val eUkraineFontFamily = FontFamily(
    Font(
        resId = R.font.e_ukraine_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.e_ukraine_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.e_ukraine_light,
        weight = FontWeight.Light,
    ),
    Font(
        resId = R.font.e_ukraine_medium,
        weight = FontWeight.Medium,
    )
)

@Immutable
data class UniTypography(
    val extraHeading: TextStyle = TextStyle(
        fontSize = 44.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = eUkraineFontFamily,
        lineHeight = 50.sp
    ),
    val heading: TextStyle = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = eUkraineFontFamily,
        lineHeight = 36.sp,
    ),
    val body: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = eUkraineFontFamily,
        lineHeight = 24.sp
    ),
    val fieldTitle: TextStyle = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
        fontFamily = eUkraineFontFamily,
        lineHeight = 18.sp
    ),
    val hint: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        fontFamily = eUkraineFontFamily,
        lineHeight = 16.sp
    ),
    val smallBody: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = eUkraineFontFamily,
        lineHeight = 14.sp
    ),
)

val LocalTypography = staticCompositionLocalOf { UniTypography() }