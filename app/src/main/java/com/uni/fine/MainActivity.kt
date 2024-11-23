package com.uni.fine

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uni.fine.ui.navigation.Screens
import com.uni.fine.ui.screens.auth.AuthScreen
import com.uni.fine.ui.screens.splash.SplashScreen
import com.uni.fine.ui.theme.UniFineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
            UniFineTheme {
                UniNavHost()
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Composable
    private fun UniNavHost() {
        val navController = rememberNavController()
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
                        navController.navigate(Screens.Home) {
                            popUpTo(Screens.Splash) { inclusive = true }
                        }
                    }
                )
            }
            composable<Screens.Auth> {
                AuthScreen(
                    onBack = { if (!navController.popBackStack()) finish() }
                )
            }
        }
    }
}
