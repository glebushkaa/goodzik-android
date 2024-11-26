package com.uni.fine.ui.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.uni.fine.ui.theme.UniFineTheme

@Composable
fun TypeChip(
    text: String,
    color: Color
) {
    Text(
        text = text,
        style = UniFineTheme.typography.hint,
        modifier = Modifier
            .background(
                color = color,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(UniFineTheme.padding.small),
        color = UniFineTheme.colors.white
    )
}