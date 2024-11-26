package com.uni.fine.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.fine.R
import com.uni.fine.model.Check
import com.uni.fine.ui.core.component.Screen
import com.uni.fine.ui.core.extension.applyIf
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.core.extension.collectAsEffect
import com.uni.fine.ui.core.extension.convertLocalDateTimeToUkrainianFormat
import com.uni.fine.ui.theme.UniFineTheme
import com.uni.fine.ui.theme.icons.DocumentEdit
import com.uni.fine.ui.theme.icons.LogOut
import com.uni.fine.ui.theme.icons.Plus
import kotlinx.collections.immutable.ImmutableList
import java.time.LocalDate

@Composable
fun HomeScreen(
    onCreateCheck: () -> Unit,
    onCheckInfo: (id: String) -> Unit,
) {
    Screen<HomeViewModel> { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()
        viewModel.sideEffect.collectAsEffect {
            when (it) {
                HomeSideEffect.CreateCheck -> onCreateCheck()
            }
        }

        HomeScreenContent(
            state = state,
            onCheckClicked = onCheckInfo,
            onCreateCheck = { viewModel.sendAction(HomeAction.CreateCheckClicked) },
            onLogOut = { viewModel.sendAction(HomeAction.LogOutClicked) }
        )
    }
}

@Composable
private fun HomeScreenContent(
    state: HomeState,
    onCheckClicked: (id: String) -> Unit,
    onCreateCheck: () -> Unit,
    onLogOut: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = UniFineTheme.padding.enormous)
                .padding(end = UniFineTheme.padding.average)
                .padding(start = UniFineTheme.padding.huge)
                .align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.home),
                style = UniFineTheme.typography.heading,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Plus,
                contentDescription = null,
                tint = UniFineTheme.colors.black,
                modifier = Modifier
                    .size(24.dp)
                    .clickableNoRipple(onClick = onCreateCheck)
            )
            Spacer(modifier = Modifier.width(UniFineTheme.padding.average))
            Icon(
                imageVector = LogOut,
                contentDescription = null,
                tint = UniFineTheme.colors.black,
                modifier = Modifier
                    .size(24.dp)
                    .clickableNoRipple(onClick = onLogOut)
            )
        }
        Spacer(modifier = Modifier.height(UniFineTheme.padding.gigantic))
        AnimatedContent(
            targetState = state.checks.isEmpty(),
            label = "",
            transitionSpec = { fadeIn() togetherWith fadeOut() }
        ) {
            if (it) {
                EmptyContent()
            } else {
                CheckList(
                    checks = state.checks,
                    onCheckClicked = onCheckClicked
                )
            }
        }
    }
}

@Composable
private fun EmptyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.4f))
        Icon(
            imageVector = DocumentEdit,
            contentDescription = stringResource(R.string.no_checks),
        )
        Text(
            text = stringResource(R.string.no_checks),
            style = UniFineTheme.typography.body,
            color = UniFineTheme.colors.black,
            modifier = Modifier.padding(UniFineTheme.padding.large)
        )
        Spacer(modifier = Modifier.weight(1.6f))
    }
}

@Composable
private fun CheckList(
    checks: ImmutableList<Check>,
    onCheckClicked: (id: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = UniFineTheme.padding.large),
        verticalArrangement = Arrangement.spacedBy(
            UniFineTheme.padding.average
        )
    ) {
        items(
            items = checks,
            key = { check -> check.id }
        ) { check ->
            CheckItem(
                modifier = Modifier
                    .animateItem()
                    .applyIf(check.id == checks.lastOrNull()?.id) {
                        navigationBarsPadding()
                    },
                check = check
            ) {
                onCheckClicked(check.id)
            }
        }
    }
}

@Composable
private fun CheckItem(
    modifier: Modifier = Modifier,
    check: Check,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = UniFineTheme.colors.black,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = UniFineTheme.colors.amber.copy(alpha = 0.1f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(UniFineTheme.padding.large)
            .clickableNoRipple(onClick = onClick),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = check.title,
                textDecoration = TextDecoration.Underline,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = UniFineTheme.typography.body.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = UniFineTheme.colors.black
            )
            Spacer(modifier = Modifier.width(UniFineTheme.padding.average))
            Text(
                text = check.createdAt.convertLocalDateTimeToUkrainianFormat(),
                maxLines = 1,
                style = UniFineTheme.typography.fieldTitle.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = UniFineTheme.colors.black
            )
        }
        Spacer(modifier = Modifier.height(UniFineTheme.padding.average))
        Text(
            text = check.summary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = UniFineTheme.typography.fieldTitle,
            color = UniFineTheme.colors.gray
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.large))
    }
}

@Preview
@Composable
private fun CheckItemPreview() {
    val check = Check(
        id = "dasda",
        title = "English title",
        summary = "Summary",
        createdAt = LocalDate.now()
    )
    CheckItem(check = check) {}
}