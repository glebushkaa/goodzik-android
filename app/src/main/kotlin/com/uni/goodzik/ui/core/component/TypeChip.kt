package com.uni.goodzik.ui.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun TypeChip(
    text: String,
    color: Color
) {
    Text(
        text = text,
        style = GoodzikTheme.typography.hint,
        modifier = Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(GoodzikTheme.padding.small),
        color = GoodzikTheme.colors.snow
    )
}