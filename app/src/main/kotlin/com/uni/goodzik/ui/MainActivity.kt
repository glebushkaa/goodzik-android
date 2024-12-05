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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uni.goodzik.ui.core.extension.collectAsEffect
import com.uni.goodzik.ui.core.extension.navigateWithFullClearedStack
import com.uni.goodzik.ui.navigation.Screens
import com.uni.goodzik.ui.screens.auth.AuthScreen
import com.uni.goodzik.ui.screens.splash.SplashScreen
import com.uni.goodzik.ui.theme.UniFineTheme
import dagger.hilt.android.AndroidEntryPoint

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

            UniFineTheme {
                UniNavHost(navController = navController)
            }
        }
    }

    @Composable
    private fun UniNavHost(navController: NavHostController) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(color = UniFineTheme.colors.white),
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

                    }
                )
            }
            composable<Screens.Auth> {
                AuthScreen(
                    onBack = { if (!navController.popBackStack()) finish() },
                    onHome = {

                    }
                )
            }
        }
    }
}
