package com.uni.fine.ui.screens.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.fine.R
import com.uni.fine.ui.core.component.Screen
import com.uni.fine.ui.core.component.TopBar
import com.uni.fine.ui.core.component.UniButton
import com.uni.fine.ui.core.component.UniTextField
import com.uni.fine.ui.core.extension.applyIf
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.core.extension.collectAsEffect
import com.uni.fine.ui.core.extension.fileName
import com.uni.fine.ui.core.extension.thinBorder
import com.uni.fine.ui.core.extension.verticalScrollbar
import com.uni.fine.ui.theme.UniFineTheme
import com.uni.fine.ui.theme.icons.Cross
import com.uni.fine.ui.theme.icons.Document

@Composable
fun UploadScreen(
    onNext: (id: String) -> Unit
) {
    Screen<UploadViewModel> { viewModel ->
        val context = LocalContext.current
        val state by viewModel.state.collectAsStateWithLifecycle()

        val keyboardController = LocalSoftwareKeyboardController.current

        viewModel.sideEffect.collectAsEffect {
            when (it) {
                is UploadSideEffect.Next -> onNext(it.id)
            }
        }

        val pdfPickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            val fileName = uri?.fileName(context) ?: return@rememberLauncherForActivityResult
            val action = UploadAction.FilePicked(
                path = uri.toString(),
                fileName = fileName
            )
            viewModel.sendAction(action)
        }

        UploadScreenContent(
            state = state,
            onUpload = { viewModel.sendAction(UploadAction.SendAction) },
            onTextChanged = { viewModel.sendAction(UploadAction.TextChanged(it)) },
            onUploadFile = { pdfPickerLauncher.launch("application/pdf") },
            onClear = {
                keyboardController?.hide()
                viewModel.sendAction(UploadAction.Clear)
            }
        )
    }
}

@Composable
private fun UploadScreenContent(
    state: UploadState,
    onUpload: () -> Unit,
    onTextChanged: (String) -> Unit,
    onClear: () -> Unit,
    onUploadFile: () -> Unit
) {
    val textScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = UniFineTheme.padding.massive),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(text = stringResource(id = R.string.upload_work_title))
        UploadFileCard(
            modifier = Modifier.padding(top = UniFineTheme.padding.enormous),
            fileName = state.fileName,
            enabled = state.text.isBlank(),
            onClear = onClear,
            onUpload = onUploadFile
        )
        Text(
            modifier = Modifier.padding(top = UniFineTheme.padding.large),
            text = stringResource(id = R.string.or),
            style = UniFineTheme.typography.fieldText
        )
        AnimatedContent(
            modifier = Modifier
                .padding(bottom = UniFineTheme.padding.small)
                .align(Alignment.End),
            targetState = state.text.isBlank(),
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "",
            content = {
                if (it) {
                    Spacer(modifier = Modifier.height(UniFineTheme.padding.huge))
                } else {
                    Text(
                        text = stringResource(R.string.clear),
                        style = UniFineTheme.typography.hint,
                        modifier = Modifier
                            .height(UniFineTheme.padding.huge)
                            .clickableNoRipple(onClick = onClear),
                        color = UniFineTheme.colors.ocean
                    )
                }
            }
        )
        UniTextField(
            innerModifier = Modifier
                .verticalScrollbar(
                    scrollState = textScrollState,
                    indicatorSize = DpSize(4.dp, 40.dp)
                )
                .verticalScroll(textScrollState),
            modifier = Modifier.weight(1f),
            hint = stringResource(id = R.string.enter_work_text),
            maxLines = Int.MAX_VALUE,
            enabled = state.fileName.isBlank(),
            centered = false,
            text = state.text,
            onTextChange = onTextChanged
        )
        Spacer(modifier = Modifier.size(UniFineTheme.padding.enormous))
        UniButton(
            modifier = Modifier.padding(
                horizontal = UniFineTheme.padding.medium,
            ),
            enabled = state.buttonEnabled,
            text = stringResource(id = R.string.next),
            onClick = onUpload,
        )
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.enormous))
    }
}

@Composable
private fun UploadFileCard(
    modifier: Modifier = Modifier,
    fileName: String,
    enabled: Boolean = true,
    onClear: () -> Unit,
    onUpload: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .thinBorder()
            .applyIf(enabled) {
                clickableNoRipple(onClick = onUpload)
            }
            .padding(
                vertical = UniFineTheme.padding.average,
                horizontal = UniFineTheme.padding.medium
            )
            .graphicsLayer {
                alpha = if (enabled) 1f else 0.6f
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Document,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(UniFineTheme.padding.small))
        if (fileName.isEmpty()) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.upload_file))
                    append(" ")
                    withStyle(UniFineTheme.typography.smallBody.toSpanStyle()) {
                        append(stringResource(id = R.string.pdf_only))
                    }
                },
                style = UniFineTheme.typography.fieldText
            )
        } else {
            Text(
                text = fileName,
                style = UniFineTheme.typography.fieldText
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        androidx.compose.animation.AnimatedVisibility(visible = fileName.isNotBlank()) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickableNoRipple(onClick = onClear),
                imageVector = Cross,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun UploadScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UniFineTheme.colors.white)
    ) {
        UploadScreenContent(
            state = UploadState(),
            onUpload = { },
            onTextChanged = { },
            onUploadFile = { },
            onClear = { }
        )
    }
}

