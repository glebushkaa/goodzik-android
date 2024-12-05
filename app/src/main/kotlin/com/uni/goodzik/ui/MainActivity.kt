package com.uni.goodzik.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uni.goodzik.ui.core.component.bottombar.BottomNavigationBar
import com.uni.goodzik.ui.core.component.bottombar.toBottomNavItem
import com.uni.goodzik.ui.core.component.bottombar.toRoute
import com.uni.goodzik.ui.core.extension.collectAsEffect
import com.uni.goodzik.ui.core.extension.navigateWithFullClearedStack
import com.uni.goodzik.ui.navigation.Screens
import com.uni.goodzik.ui.screens.auth.AuthScreen
import com.uni.goodzik.ui.screens.guides.GuidesScreen
import com.uni.goodzik.ui.screens.news.NewsScreen
import com.uni.goodzik.ui.screens.profile.ProfileScreen
import com.uni.goodzik.ui.screens.splash.SplashScreen
import com.uni.goodzik.ui.theme.GoodzikTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val lightBarStyle = SystemBarStyle.light(
            scrim = Color.TRANSPARENT,
            darkScrim = Color.TRANSPARENT
        )
        enableEdgeToEdge(
            statusBarStyle = lightBarStyle,
            navigationBarStyle = lightBarStyle
        )
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            viewModel.sideEffect.collectAsEffect {
                when (it) {
                    MainSideEffect.LogOut -> navController.navigateWithFullClearedStack(Screens.Auth)
                }
            }
            val entry by navController.currentBackStackEntryFlow
                .map {
                    it.destination.route
                        ?.substringAfterLast('.')
                        ?.toBottomNavItem()
                }
                .collectAsState(null)

            GoodzikTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    UniNavHost(navController = navController)
                    BottomNavigationBar(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = GoodzikTheme.padding.massive),
                        shown = entry != null,
                        currentTab = entry,
                        onTabSelected = {
                            navController.navigate(it.toRoute()) {
                                popUpTo(Screens.Splash) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    }

    @Composable
    private fun UniNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController
    ) {
        NavHost(
            modifier = modifier
                .fillMaxSize()
                .background(color = GoodzikTheme.colors.snow),
            navController = navController,
            startDestination = Screens.Splash,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            composable<Screens.Splash> {
                SplashScreen(
                    onAuth = {
                        navController.navigate(Screens.Auth) {
                            popUpTo(Screens.Splash) { inclusive = true }
                        }
                    },
                    onHome = {
                        navController.navigate(Screens.Guides) {
                            popUpTo(Screens.Splash) { inclusive = true }
                        }
                    }
                )
            }
            composable<Screens.Auth> {
                AuthScreen(
                    onBack = { if (!navController.popBackStack()) finish() },
                    onHome = {
                        navController.navigate(Screens.Guides) {
                            popUpTo(Screens.Splash) { inclusive = true }
                        }
                    }
                )
            }
            composable<Screens.Guides> {
                GuidesScreen()
            }
            composable<Screens.News> {
                NewsScreen()
            }
            composable<Screens.Profile> {
                ProfileScreen()
            }
        }
    }
}
