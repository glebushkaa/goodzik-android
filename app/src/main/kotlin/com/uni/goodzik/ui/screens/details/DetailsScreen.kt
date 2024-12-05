package com.uni.goodzik.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.uni.goodzik.R
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.core.extension.verticalScrollbar
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun DetailsScreen(
    id: String,
    onNext: () -> Unit,
    onChat: () -> Unit,
) {
    Screen<DetailsViewModel, DetailsViewModel.Factory>(
        creationCallback = { factory -> factory.create(id) }
    ) { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        DetailsScreenContent(
            state = state,
            onNext = onNext,
            onChat = onChat
        )
    }
}

@Composable
private fun DetailsScreenContent(
    state: DetailsState,
    onNext: () -> Unit,
    onChat: () -> Unit,
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
        ) {
            Text(
                text = stringResource(R.string.details),
                style = GoodzikTheme.typography.heading,
                color = GoodzikTheme.colors.black
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop,
                model = state.imageUrl,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            Text(
                text = state.author,
                color = GoodzikTheme.colors.description,
                style = GoodzikTheme.typography.hint
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.small))
            Text(
                text = state.title,
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
                    .verticalScroll(scrollState)
                    .padding(bottom = GoodzikTheme.padding.colossal)
                    .verticalScrollbar(
                        scrollState = scrollState,
                        color = GoodzikTheme.colors.black,
                        indicatorSize = DpSize(2.dp, 40.dp)
                    )
                    .padding(end = GoodzikTheme.padding.average),
                text = state.description,
                color = GoodzikTheme.colors.description,
                style = GoodzikTheme.typography.fieldTitle
            )
        }
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
                    .clickableNoRipple(onClick = onChat)
                    .padding(GoodzikTheme.padding.large)
                    .size(24.dp),
                tint = GoodzikTheme.colors.snow,
                painter = painterResource(R.drawable.message),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(GoodzikTheme.padding.large))
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
