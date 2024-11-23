package com.uni.fine.ui.screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.fine.R
import com.uni.fine.ui.core.component.OnBackEventListener
import com.uni.fine.ui.core.component.TopBar
import com.uni.fine.ui.core.component.UniButton
import com.uni.fine.ui.core.component.UniTextField
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.core.extension.collectAsEffect
import com.uni.fine.ui.theme.UniFineTheme

// TODO Add Password Icon

@Composable
fun AuthScreen(
    onBack: () -> Unit,
) {
    val viewModel = hiltViewModel<AuthViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect {
        when (it) {
            AuthSideEffect.Back -> onBack()
        }
    }

    OnBackEventListener(
        onBack = { viewModel.sendAction(AuthAction.Back) },
        disableSystemBackPressed = true,
    )

    AuthScreenContent(
        state = state,
        onCreateAccount = { viewModel.sendAction(AuthAction.CreateAccount) },
        onCreateAccountMode = { viewModel.sendAction(AuthAction.CreateAccountMode) },
        onPasswordUpdate = { viewModel.sendAction(AuthAction.UpdatePassword(it)) },
        onEmailUpdate = { viewModel.sendAction(AuthAction.UpdateEmail(it)) },
        onConfirmPasswordUpdate = { viewModel.sendAction(AuthAction.UpdateConfirmPassword(it)) },
        onLogin = { viewModel.sendAction(AuthAction.Login) },
    )
}

@Composable
private fun AuthScreenContent(
    state: AuthState,
    onCreateAccount: () -> Unit,
    onCreateAccountMode: () -> Unit,
    onLogin: () -> Unit,
    onPasswordUpdate: (String) -> Unit,
    onEmailUpdate: (String) -> Unit,
    onConfirmPasswordUpdate: (String) -> Unit,
) {
    val isLogin by remember(state) {
        derivedStateOf { state.type == AuthState.AuthType.Login }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
            .padding(horizontal = UniFineTheme.padding.gigantic)
    ) {
        TopBar(
            modifier = Modifier.heightIn(min = 180.dp),
            text = if (state.type == AuthState.AuthType.Login) {
                stringResource(R.string.login)
            } else {
                stringResource(R.string.create_account)
            },
            textStyle = UniFineTheme.typography.extraHeading
        )
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.huge))
        Text(
            text = stringResource(R.string.email),
            style = UniFineTheme.typography.body,
        )
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.small))
        UniTextField(
            text = state.email,
            hint = stringResource(R.string.email_hint),
            onTextChange = onEmailUpdate,
        )
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.huge))
        Text(
            text = stringResource(R.string.password),
            style = UniFineTheme.typography.body,
        )
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.small))
        UniTextField(
            text = state.password,
            hint = stringResource(R.string.password_hint),
            visualTransformation = PasswordVisualTransformation(),
            onTextChange = onPasswordUpdate,
        )
        if (isLogin) {
            Text(
                modifier = Modifier
                    .padding(top = UniFineTheme.padding.average)
                    .clickableNoRipple(onClick = onCreateAccountMode),
                text = stringResource(R.string.create_new_account),
                color = UniFineTheme.colors.ocean,
                textDecoration = TextDecoration.Underline,
                style = UniFineTheme.typography.hint,
            )
        }
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.huge))
        AnimatedVisibility(
            visible = !isLogin,
            enter = slideInHorizontally { it * 2 },
            exit = slideOutHorizontally { it * 2 },
        ) {
            Column {
                Text(
                    text = stringResource(R.string.confirm_password),
                    style = UniFineTheme.typography.body,
                )
                Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.small))
                UniTextField(
                    text = state.confirmPassword,
                    hint = stringResource(R.string.confirm_password_hint),
                    visualTransformation = PasswordVisualTransformation(),
                    onTextChange = onConfirmPasswordUpdate,
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        UniButton(
            modifier = Modifier.padding(
                horizontal = UniFineTheme.padding.medium,
            ),
            enabled = state.buttonEnabled,
            text = if (isLogin) {
                stringResource(R.string.login)
            } else {
                stringResource(R.string.create)
            },
            onClick = if (isLogin) onLogin else onCreateAccount,
        )
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.enormous))
    }
}