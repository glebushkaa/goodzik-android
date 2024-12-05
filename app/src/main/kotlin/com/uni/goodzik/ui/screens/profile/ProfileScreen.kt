package com.uni.goodzik.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.goodzik.R
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun ProfileScreen() {
    Screen<ProfileViewModel> { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        ProfileScreenContent(state = state)
    }
}

@Composable
private fun ProfileScreenContent(state: ProfileState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GoodzikTheme.colors.milk)
            .systemBarsPadding()
            .padding(top = GoodzikTheme.padding.huge)
            .padding(horizontal = GoodzikTheme.padding.huge)
    ) {
        Text(
            text = stringResource(R.string.profile),
            style = GoodzikTheme.typography.heading,
            color = GoodzikTheme.colors.black
        )
        Spacer(modifier = Modifier.padding(GoodzikTheme.padding.large))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .background(
                    color = GoodzikTheme.colors.snow,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(
                    vertical = GoodzikTheme.padding.large,
                    horizontal = GoodzikTheme.padding.average
                )
        ) {
            Text(
                text = state.username,
                style = GoodzikTheme.typography.hugeBody,
                color = GoodzikTheme.colors.black
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.average))
            Text(
                text = state.email,
                style = GoodzikTheme.typography.fieldTitle,
                color = GoodzikTheme.colors.black
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            SettingButton(
                text = stringResource(R.string.chat_history),
                icon = painterResource(R.drawable.chat),
                color = GoodzikTheme.colors.black
            ) { }
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
            SettingButton(
                text = stringResource(R.string.about_us),
                icon = painterResource(R.drawable.info),
                color = GoodzikTheme.colors.black
            ) { }
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
            SettingButton(
                text = stringResource(R.string.log_out),
                icon = painterResource(R.drawable.logout),
                color = GoodzikTheme.colors.red
            ) { }
        }
    }
}

@Composable
private fun SettingButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickableNoRipple(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(GoodzikTheme.padding.medium))
        Text(
            text = text,
            style = GoodzikTheme.typography.fieldText,
            color = color
        )
    }
}

@Composable
@Preview
private fun ProfileScreenContentPreview() {
    ProfileScreenContent(ProfileState("Goodzik", "mail@mail.com"))
}
