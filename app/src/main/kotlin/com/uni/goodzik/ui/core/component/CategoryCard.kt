package com.uni.goodzik.ui.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    name: String,
) {
    Text(
        text = name,
        color = GoodzikTheme.colors.green,
        style = GoodzikTheme.typography.smallBody,
        modifier = modifier
            .background(
                color = GoodzikTheme.colors.green.copy(0.3f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                horizontal = 6.dp,
                vertical = 2.dp
            )
    )
}