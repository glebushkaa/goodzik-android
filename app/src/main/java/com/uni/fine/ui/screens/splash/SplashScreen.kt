package com.uni.fine.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.uni.fine.R
import com.uni.fine.ui.theme.UniFineTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onAuth: () -> Unit,
    onHome: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onAuth()
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