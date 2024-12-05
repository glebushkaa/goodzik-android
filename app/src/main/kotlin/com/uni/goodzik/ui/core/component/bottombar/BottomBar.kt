package com.uni.goodzik.ui.core.component.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun BottomNavigationBar(
    shown: Boolean,
    currentTab: BottomNavItem?,
    onTabSelected: (BottomNavItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = shown,
        enter = slideInVertically { it },
        exit = fadeOut()
    ) {
        Row(
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
                    horizontal = GoodzikTheme.padding.medium,
                    vertical = GoodzikTheme.padding.small
                ),
            horizontalArrangement = Arrangement.spacedBy(
                GoodzikTheme.padding.average
            ),
        ) {
            BottomNavItem.entries.forEach {
                val selected = it == currentTab
                BottomBarItem(
                    selected = selected,
                    onClick = {
                        if(selected) return@BottomBarItem
                        onTabSelected(it)
                    },
                    icon = painterResource(id = it.icon),
                )
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
    Icon(
        painter = icon,
        contentDescription = null,
        tint = if (selected) Color.White else Color.Black,
        modifier = modifier
            .size(50.dp)
            .background(
                color = if (selected) Color.Black else Color.Transparent,
                shape = CircleShape
            )
            .clickableNoRipple(onClick)
            .padding(GoodzikTheme.padding.average)
    )
}