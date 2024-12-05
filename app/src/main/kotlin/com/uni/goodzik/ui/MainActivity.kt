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
import androidx.navigation.toRoute
import com.uni.goodzik.ui.core.component.bottombar.BottomNavigationBar
import com.uni.goodzik.ui.core.component.bottombar.toBottomNavItem
import com.uni.goodzik.ui.core.component.bottombar.toRoute
import com.uni.goodzik.ui.core.extension.collectAsEffect
import com.uni.goodzik.ui.core.extension.navigateWithFullClearedStack
import com.uni.goodzik.ui.navigation.Screens
import com.uni.goodzik.ui.screens.auth.AuthScreen
import com.uni.goodzik.ui.screens.chat.ChatScreen
import com.uni.goodzik.ui.screens.details.DetailsScreen
import com.uni.goodzik.ui.screens.guides.GuidesScreen
import com.uni.goodzik.ui.screens.news.NewsScreen
import com.uni.goodzik.ui.screens.news_details.NewsDetailsScreen
import com.uni.goodzik.ui.screens.profile.ProfileScreen
import com.uni.goodzik.ui.screens.splash.SplashScreen
import com.uni.goodzik.ui.screens.steps.StepsScreen
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
                GuidesScreen { id ->
                    navController.navigate(Screens.Details(id))
                }
            }
            composable<Screens.News> {
                NewsScreen {
                    navController.navigate(Screens.NewsDetails(it))
                }
            }
            composable<Screens.Profile> {
                ProfileScreen()
            }
            composable<Screens.Details> {
                val id = it.toRoute<Screens.Details>().id
                DetailsScreen(
                    id = id,
                    onNext = {
                        navController.navigate(Screens.Steps(id)) {
                            popUpTo(Screens.Guides) { inclusive = false }
                        }
                    },
                    onChat = {
                        navController.navigate(Screens.Chat(id)) {
                            popUpTo(Screens.Guides) { inclusive = false }
                        }
                    }
                )
            }
            composable<Screens.Steps> {
                val id = it.toRoute<Screens.Steps>().id
                StepsScreen(id)
            }

            composable<Screens.NewsDetails> {
                val id = it.toRoute<Screens.NewsDetails>().id
                NewsDetailsScreen(id)
            }

            composable<Screens.Chat> {
                val id = it.toRoute<Screens.Chat>().id
                ChatScreen(id)
            }
        }
    }
}
