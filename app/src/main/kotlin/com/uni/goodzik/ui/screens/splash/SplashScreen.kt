package com.uni.goodzik.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uni.goodzik.R
import com.uni.goodzik.ui.core.extension.collectAsEffect
import com.uni.goodzik.ui.theme.UniFineTheme

@Composable
fun SplashScreen(
    onAuth: () -> Unit,
    onHome: () -> Unit
) {
    val viewModel = hiltViewModel<SplashViewModel>()
    viewModel.sideEffect.collectAsEffect {
        when (it) {
            is SplashSideEffect.NavigateToAuth -> onAuth()
            is SplashSideEffect.NavigateToHome -> onHome()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = UniFineTheme.padding.huge,
            alignment = Alignment.CenterVertically
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(128.dp),
            painter = painterResource(id = R.drawable.uni_fine),
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.app_name),
            style = UniFineTheme.typography.extraHeading,
            color = UniFineTheme.colors.black
        )
    }
}