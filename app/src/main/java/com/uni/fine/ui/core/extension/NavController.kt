package com.uni.fine.ui.core.extension

import androidx.navigation.NavController
import com.uni.fine.ui.navigation.Screens

internal fun NavController.navigateSingleTop(screen: Screens) {
    navigate(screen) {
        launchSingleTop = true
        popUpTo(0) { inclusive = true }
    }
}

internal fun NavController.navigateWithFullClearedStack(screen: Screens) {
    navigate(screen) {
        launchSingleTop = true
        popUpTo(graph.startDestinationId) { inclusive = true }
    }
}

internal fun NavController.navigateWithClearedStack(
    screen: Screens,
    clearTo: Screens,
    inclusive: Boolean = true,
) {
    navigate(screen) {
        launchSingleTop = true
        popUpTo(clearTo) { this.inclusive = inclusive }
    }
}