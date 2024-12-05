package com.uni.goodzik.ui.screens.steps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.uni.goodzik.R
import com.uni.goodzik.model.Step
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.core.extension.verticalScrollbar
import com.uni.goodzik.ui.theme.GoodzikTheme
import kotlinx.coroutines.launch

@Composable
fun StepsScreen(id: String) {
    Screen<StepsViewModel, StepsViewModel.Factory>(
        creationCallback = { factory -> factory.create(id) }
    ) { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        StepsScreenContent(
            state = state
        )
    }
}

@Composable
private fun StepsScreenContent(state: StepsState) {
    val pagerState = rememberPagerState(
        pageCount = {
            state.steps.size
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GoodzikTheme.colors.milk)
            .systemBarsPadding()
            .padding(top = GoodzikTheme.padding.huge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = GoodzikTheme.padding.massive
                ),
                text = stringResource(R.string.steps),
                style = GoodzikTheme.typography.heading,
                color = GoodzikTheme.colors.black
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
            ) {
                StepContent(step = state.steps[it])
            }
        }
        PagerController(
            modifier = Modifier
                .padding(bottom = GoodzikTheme.padding.huge)
                .align(Alignment.BottomCenter),
            pagerState = pagerState
        )
    }
}

@Composable
private fun PagerController(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .padding(horizontal = GoodzikTheme.padding.gigantic)
            .fillMaxWidth()
            .height(54.dp)
            .background(
                color = GoodzikTheme.colors.snow.copy(0.95f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = GoodzikTheme.padding.large)
    ) {
        PagerControllerButton(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .rotate(180f),
            enabled = pagerState.currentPage > 0
        ) {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "${pagerState.currentPage + 1}/${pagerState.pageCount}",
            color = GoodzikTheme.colors.black,
            style = GoodzikTheme.typography.fieldText,
        )
        PagerControllerButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            enabled = pagerState.currentPage < pagerState.pageCount - 1
        ) {
            scope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
}

@Composable
private fun PagerControllerButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Icon(
        modifier = modifier
            .size(24.dp)
            .clickableNoRipple(onClick),
        tint = if (enabled) GoodzikTheme.colors.black else GoodzikTheme.colors.disabled,
        painter = painterResource(R.drawable.next),
        contentDescription = null,
    )
}

@Composable
private fun StepContent(step: Step) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.padding(
            horizontal = GoodzikTheme.padding.massive
        )
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
            model = step.image,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.huge))
        Text(
            text = step.guideTitle,
            color = GoodzikTheme.colors.description,
            style = GoodzikTheme.typography.hint
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.small))
        Text(
            text = step.name,
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
                .weight(1f)
                .verticalScroll(scrollState)
                .padding(
                    bottom = GoodzikTheme.padding.immense,
                    end = GoodzikTheme.padding.average
                )
                .verticalScrollbar(
                    scrollState = scrollState,
                    color = GoodzikTheme.colors.black,
                    indicatorSize = DpSize(2.dp, 40.dp)
                ),
            text = step.description,
            color = GoodzikTheme.colors.description,
            style = GoodzikTheme.typography.fieldTitle
        )
    }
}