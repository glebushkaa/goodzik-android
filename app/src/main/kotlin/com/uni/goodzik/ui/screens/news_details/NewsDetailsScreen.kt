package com.uni.goodzik.ui.screens.news_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.uni.goodzik.R
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.verticalScrollbar
import com.uni.goodzik.ui.theme.GoodzikTheme
import kotlin.math.absoluteValue

@Composable
fun NewsDetailsScreen(id: String) {
    Screen<NewsDetailsViewModel, NewsDetailsViewModel.Factory>(
        creationCallback = { factory -> factory.create(id) }
    ) { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        NewsDetailsScreenContent(state = state)
    }
}

@Composable
private fun NewsDetailsScreenContent(
    state: NewsDetailsState
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState { state.images.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GoodzikTheme.colors.milk)
            .systemBarsPadding()
            .padding(top = GoodzikTheme.padding.huge)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = GoodzikTheme.padding.massive),
            text = stringResource(R.string.news_details),
            style = GoodzikTheme.typography.heading,
            color = GoodzikTheme.colors.black
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(
                horizontal = GoodzikTheme.padding.massive
            )
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(horizontal = GoodzikTheme.padding.medium)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .graphicsLayer {
                        val pageOffset = ((pagerState.currentPage - it) +
                                pagerState.currentPageOffsetFraction).absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                contentScale = ContentScale.Crop,
                model = state.images[it],
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier.padding(
                horizontal = GoodzikTheme.padding.massive
            )
        ) {
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
            Text(
                modifier = Modifier,
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
                    .padding(end = GoodzikTheme.padding.large),
                text = state.description,
                color = GoodzikTheme.colors.description,
                style = GoodzikTheme.typography.fieldTitle
            )
        }
    }
}