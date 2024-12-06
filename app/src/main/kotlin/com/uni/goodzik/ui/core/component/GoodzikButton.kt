package com.uni.goodzik.ui.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun GoodzikButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = GoodzikTheme.colors.amber,
    textColor: Color = GoodzikTheme.colors.snow,
    icon: @Composable (() -> Unit)? = null,
    loading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        onClick = {
            if (!loading) onClick()
        },
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (loading) color.copy(alpha = 0.7f) else color,
            disabledContainerColor = GoodzikTheme.colors.gray,
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AnimatedVisibility(
                modifier = Modifier.padding(end = GoodzikTheme.padding.large),
                visible = loading,
                enter = fadeIn() + expandHorizontally(),
                exit = fadeOut() + shrinkHorizontally(),
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    color = GoodzikTheme.colors.snow,
                    strokeWidth = 2.dp
                )
            }
            if (icon != null) icon()
            Spacer(modifier = Modifier.width(GoodzikTheme.padding.medium))
            Text(
                text = text,
                color = textColor,
                style = GoodzikTheme.typography.body
            )
        }
    }
}