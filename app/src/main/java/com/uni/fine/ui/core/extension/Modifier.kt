package com.uni.fine.ui.core.extension

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.uni.fine.ui.theme.UniFineTheme

@Composable
fun Modifier.clickableNoRipple(
    onClick: () -> Unit,
) = clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    onClick = onClick
)

@Composable
fun Modifier.applyIf(condition: Boolean, modifier: @Composable Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

fun Modifier.verticalScrollbar(
    scrollState: ScrollState,
    indicatorSize: DpSize = DpSize(3.dp, 100.dp),
    paddingEnd: Dp = 4.dp,
    color: Color = Color.LightGray,
) = drawWithContent {
    drawContent()
    if (scrollState.canScrollForward || scrollState.canScrollBackward) {
        val portion = scrollState.value.toFloat() / scrollState.maxValue
        val indicatorY = (size.height - indicatorSize.height.toPx()) * portion
        val indicatorPosition = Offset(
            x = size.width - indicatorSize.width.toPx() - paddingEnd.toPx(),
            y = indicatorY
        )
        drawRoundRect(
            color = color,
            topLeft = indicatorPosition,
            size = indicatorSize.toSize(),
            cornerRadius = CornerRadius(
                x = indicatorSize.width.toPx() / 2,
                y = indicatorSize.width.toPx() / 2
            )
        )
    }
}

@Composable
fun Modifier.thinBorder(
    color: Color = UniFineTheme.colors.black
) = border(
    width = 1.dp,
    color = color,
    shape = RoundedCornerShape(10.dp)
)