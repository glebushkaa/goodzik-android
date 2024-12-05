package com.uni.goodzik.ui.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.goodzik.ui.core.BaseViewModel
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
inline fun <reified VM : BaseViewModel> Screen(
    crossinline content: @Composable (VM) -> Unit
) {
    val viewModel = hiltViewModel<VM>()
    BaseContent(
        viewModel = viewModel,
        content = content,
    )
}

@Composable
inline fun <reified VM : BaseViewModel, reified VMF> Screen(
    noinline creationCallback: (VMF) -> VM,
    content: @Composable (VM) -> Unit,
) {
    val viewModel = hiltViewModel<VM, VMF>(
        creationCallback = creationCallback
    )
    BaseContent(
        viewModel = viewModel,
        content = content,
    )
}

@Composable
inline fun <reified VM : BaseViewModel> BaseContent(
    viewModel: VM,
    content: @Composable (VM) -> Unit
) {
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val toast by viewModel.toast.collectAsStateWithLifecycle(null)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        content(viewModel)

        toast?.let {
            ToastMessage(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxSize()
                    .padding(top = GoodzikTheme.padding.massive)
                    .padding(horizontal = GoodzikTheme.padding.massive),
                toastMessageData = it,
            )
        }

        AnimatedVisibility(
            visible = loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GoodzikTheme.colors.black.copy(alpha = 0.7f))
                    .clickableNoRipple { },
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = GoodzikTheme.colors.amber,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}