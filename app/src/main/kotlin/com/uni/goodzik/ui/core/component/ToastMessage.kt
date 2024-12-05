package com.uni.goodzik.ui.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.uni.goodzik.ui.theme.GoodzikTheme
import kotlinx.coroutines.delay

private const val MAX_ANIMATION_DURATION = 600

@Composable
fun ToastMessage(
    toastMessageData: ToastMessageData,
    modifier: Modifier = Modifier,
) {

    var visible by remember { mutableStateOf(false) }
    val animationDuration = (toastMessageData.duration / 2).coerceAtMost(MAX_ANIMATION_DURATION)

    Box(
        modifier = modifier
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { -it * 2 },
                animationSpec = tween(durationMillis = animationDuration)
            ),
            exit = slideOutVertically(
                targetOffsetY = { -it * 2 },
                animationSpec = tween(durationMillis = animationDuration)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = toastMessageData.type.toColor(),
                        shape = RoundedCornerShape(8.dp)
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = GoodzikTheme.padding.large,
                        end = GoodzikTheme.padding.medium,
                        top = GoodzikTheme.padding.large,
                        bottom = GoodzikTheme.padding.large,
                    ),
                    text = toastMessageData.text,
                    style = GoodzikTheme.typography.fieldText,
                    color = GoodzikTheme.colors.snow,
                )
            }
        }
    }


    LaunchedEffect(toastMessageData) {
        visible = true
        delay(toastMessageData.duration.toLong())
        visible = false
    }
}

class ToastMessageData(
    val text: String,
    val type: Type,
    val duration: Int = 600,
) {
    enum class Type {
        Error, Success
    }
}

@Composable
fun ToastMessageData.Type?.toColor(): Color {
    return when (this) {
        ToastMessageData.Type.Error -> GoodzikTheme.colors.red
        else -> GoodzikTheme.colors.green
    }
}