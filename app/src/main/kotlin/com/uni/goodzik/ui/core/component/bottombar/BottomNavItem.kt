package com.uni.goodzik.ui.core.component.bottombar

import androidx.annotation.DrawableRes
import com.uni.goodzik.R
import com.uni.goodzik.ui.navigation.Screens

enum class BottomNavItem(@DrawableRes val icon: Int) {
    News(icon = R.drawable.news),
    Guides(icon = R.drawable.guides),
    Profile(icon = R.drawable.profile),
    Donate(icon = R.drawable.donate),
}

fun BottomNavItem.toRoute() = when (this) {
    BottomNavItem.Profile -> Screens.Profile
    BottomNavItem.News -> Screens.News
    BottomNavItem.Guides -> Screens.Guides
    BottomNavItem.Donate -> Screens.Donate
}

fun String.toBottomNavItem(): BottomNavItem? = when (this) {
    "Profile" -> BottomNavItem.Profile
    "News" -> BottomNavItem.News
    "Guides" -> BottomNavItem.Guides
    "Donate" -> BottomNavItem.Donate
    else -> null
}