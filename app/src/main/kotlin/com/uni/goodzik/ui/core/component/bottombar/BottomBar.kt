package com.uni.goodzik.ui.core.component.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun BottomNavigationBar(
    shown: Boolean,
    currentTab: BottomNavItem?,
    onTabSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val selectedIndex by remember(currentTab) {
        derivedStateOf { BottomNavItem.entries.indexOfFirst { it == currentTab } }
    }
    val horizontalPadding = GoodzikTheme.padding.medium
    val spacedBy = GoodzikTheme.padding.average

    val xOffset by remember(selectedIndex) {
        derivedStateOf { selectedIndex * (44.dp + spacedBy) }
    }
    val animatedXOffset by animateDpAsState(
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessVeryLow
        ),
        targetValue = xOffset,
        label = "",
    )

    AnimatedVisibility(
        modifier = modifier,
        visible = shown,
        enter = slideInVertically { it },
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(50.dp)
                )
                .background(
                    color = GoodzikTheme.colors.snow,
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(
                    horizontal = horizontalPadding,
                    vertical = GoodzikTheme.padding.small
                )
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .offset {
                        IntOffset(x = animatedXOffset.roundToPx(), y = 0)
                    }
                    .background(
                        color = GoodzikTheme.colors.black,
                        shape = CircleShape
                    )
            )
            Row(horizontalArrangement = Arrangement.spacedBy(spacedBy)) {
                BottomNavItem.entries.forEachIndexed { index, it ->
                    BottomBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            if (selectedIndex == index) return@BottomBarItem
                            onTabSelected(it)
                        },
                        icon = painterResource(id = it.icon),
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: Painter,
    onClick: () -> Unit
) {
    val animatedColor by animateColorAsState(
        targetValue = if (selected) GoodzikTheme.colors.snow else GoodzikTheme.colors.black,
        label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )

    Icon(
        painter = icon,
        contentDescription = null,
        tint = animatedColor,
        modifier = modifier
            .size(44.dp)
            .clickableNoRipple(onClick)
            .padding(GoodzikTheme.padding.average)
    )
}