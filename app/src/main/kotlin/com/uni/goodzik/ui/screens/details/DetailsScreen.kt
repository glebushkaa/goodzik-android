package com.uni.goodzik.ui.screens.details

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.uni.goodzik.R
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.screens.chat.Message
import com.uni.goodzik.ui.screens.chat.MessageTextField
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun DetailsScreen(
    id: String,
    onNext: () -> Unit,
) {
    Screen<DetailsViewModel, DetailsViewModel.Factory>(
        creationCallback = { factory -> factory.create(id) }
    ) { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        DetailsScreenContent(
            state = state,
            onNext = onNext,
            onMessageUpdated = { viewModel.sendAction(DetailsAction.MessageUpdated(it)) },
            onSend = { viewModel.sendAction(DetailsAction.Send) },
            onDownloadFile = { url, order ->
                viewModel.sendAction(DetailsAction.DownloadFile(url, order))
            }
        )
    }
}

@Composable
private fun DetailsScreenContent(
    state: DetailsState,
    onNext: () -> Unit,
    onMessageUpdated: (String) -> Unit,
    onSend: () -> Unit,
    onDownloadFile: (String, Int) -> Unit,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = GoodzikTheme.colors.milk)
                .systemBarsPadding()
                .padding(top = GoodzikTheme.padding.huge)
                .padding(horizontal = GoodzikTheme.padding.massive)
                .verticalScroll(scrollState)
                .padding(bottom = GoodzikTheme.padding.colossal)
        ) {
            Text(
                text = state.title,
                style = GoodzikTheme.typography.heading,
                color = GoodzikTheme.colors.black
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
            state.videoUrl?.let {
                AndroidView(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .fillMaxWidth()
                        .height(240.dp),
                    factory = { context ->
                        WebView(context).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            settings.apply {
                                javaScriptEnabled = true
                                loadWithOverviewMode = true
                                useWideViewPort = true
                            }
                            webViewClient = WebViewClient()
                            loadUrl(it)
                        }
                    },
                )
                Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            }
            AnimatedVisibility(
                visible = state.schemes.isNotEmpty()
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.schemes),
                        color = GoodzikTheme.colors.black,
                        style = GoodzikTheme.typography.fieldText
                    )
                    Spacer(modifier = Modifier.height(GoodzikTheme.padding.medium))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        state.schemes.forEachIndexed { index, url ->
                            Text(
                                modifier = Modifier.clickableNoRipple {
                                    onDownloadFile(url, index)
                                },
                                text = stringResource(R.string.scheme, index + 1),
                                color = GoodzikTheme.colors.ocean,
                                style = GoodzikTheme.typography.fieldTitle
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            }
            AnimatedVisibility(
                visible = state.exampleImages.isNotEmpty()
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.done_works),
                        color = GoodzikTheme.colors.black,
                        style = GoodzikTheme.typography.fieldText
                    )
                    Spacer(modifier = Modifier.height(GoodzikTheme.padding.medium))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            GoodzikTheme.padding.large
                        )
                    ) {
                        items(state.exampleImages) { image ->
                            AsyncImage(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Crop,
                                model = image,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            Text(
                text = stringResource(R.string.details),
                color = GoodzikTheme.colors.black,
                style = GoodzikTheme.typography.fieldText
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = GoodzikTheme.padding.medium)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = GoodzikTheme.colors.description
            )
            Text(
                modifier = Modifier.padding(end = GoodzikTheme.padding.average),
                text = state.description,
                color = GoodzikTheme.colors.description,
                style = GoodzikTheme.typography.fieldTitle
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            Text(
                text = stringResource(R.string.author),
                color = GoodzikTheme.colors.black,
                style = GoodzikTheme.typography.fieldText
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = GoodzikTheme.padding.medium)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = GoodzikTheme.colors.description
            )
            Text(
                modifier = Modifier
                    .padding(end = GoodzikTheme.padding.average),
                text = state.author,
                color = GoodzikTheme.colors.description,
                style = GoodzikTheme.typography.fieldTitle
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            Text(
                text = stringResource(R.string.comments),
                color = GoodzikTheme.colors.black,
                style = GoodzikTheme.typography.fieldText
            )
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = GoodzikTheme.padding.medium)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = GoodzikTheme.colors.description
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(
                    GoodzikTheme.padding.average
                ),
            ) {
                state.comments.forEach { message ->
                    Message(
                        modifier = Modifier,
                        message = message.text,
                        incoming = false,
                        author = message.author,
                    )
                }
            }
            AnimatedVisibility(state.comments.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_comments),
                    color = GoodzikTheme.colors.description,
                    style = GoodzikTheme.typography.fieldTitle
                )
            }
            Spacer(Modifier.height(24.dp))
            MessageTextField(
                modifier = Modifier.padding(
                    vertical = GoodzikTheme.padding.huge
                ),
                text = state.messageText,
                onTextChange = onMessageUpdated,
                onSend = onSend
            )
        }
        if(!state.isStepsEmpty) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(GoodzikTheme.padding.huge)
            ) {
                Icon(
                    modifier = Modifier
                        .background(
                            color = GoodzikTheme.colors.black,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickableNoRipple(onClick = onNext)
                        .padding(GoodzikTheme.padding.large)
                        .size(24.dp),
                    tint = GoodzikTheme.colors.snow,
                    painter = painterResource(R.drawable.next),
                    contentDescription = null,
                )
            }
        }
    }
}
