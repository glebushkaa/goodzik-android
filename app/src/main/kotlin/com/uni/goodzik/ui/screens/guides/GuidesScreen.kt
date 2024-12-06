package com.uni.goodzik.ui.screens.guides

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.goodzik.R
import com.uni.goodzik.model.Category
import com.uni.goodzik.model.Guide
import com.uni.goodzik.ui.core.component.CategoryCard
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.applyIf
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.core.extension.convertLocalDateTimeToUkrainianFormat
import com.uni.goodzik.ui.theme.GoodzikTheme
import java.time.LocalDate

@Composable
fun GuidesScreen(
    onDetails: (id: String) -> Unit
) {
    Screen<GuidesViewModel> { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        GuidesScreenContent(
            state = state,
            onGuideClick = onDetails
        )
    }
}

@Composable
private fun GuidesScreenContent(
    state: GuidesState,
    onGuideClick: (id: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GoodzikTheme.colors.milk)
            .statusBarsPadding()
            .padding(top = GoodzikTheme.padding.huge)
            .padding(horizontal = GoodzikTheme.padding.huge)
    ) {
        Text(
            text = stringResource(R.string.guides),
            style = GoodzikTheme.typography.heading,
            color = GoodzikTheme.colors.black
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                GoodzikTheme.padding.large
            ),
            contentPadding = PaddingValues(
                bottom = GoodzikTheme.padding.colossal
            )
        ) {
            items(
                items = state.guides,
                key = { guide -> guide.id }
            ) { guide ->
                val isLast = guide.id == state.guides.lastOrNull()?.id
                GuideCard(
                    modifier = Modifier
                        .animateItem()
                        .applyIf(isLast) { navigationBarsPadding() },
                    guide = guide
                ) {
                    onGuideClick(guide.id)
                }
            }
        }
    }
}

@Composable
private fun GuideCard(
    modifier: Modifier = Modifier,
    guide: Guide,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                color = GoodzikTheme.colors.snow,
                shape = RoundedCornerShape(12.dp)
            )
            .clickableNoRipple(onClick = onClick)
            .padding(GoodzikTheme.padding.average)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = guide.title,
                maxLines = 1,
                style = GoodzikTheme.typography.body,
                color = GoodzikTheme.colors.black,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(GoodzikTheme.padding.average))
            Text(
                text = guide.date.convertLocalDateTimeToUkrainianFormat(),
                style = GoodzikTheme.typography.hint,
            )
        }
        Text(
            text = guide.description,
            style = GoodzikTheme.typography.fieldTitle,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = GoodzikTheme.colors.description,
            modifier = Modifier.padding(
                top = GoodzikTheme.padding.medium,
                end = GoodzikTheme.padding.average
            )
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = GoodzikTheme.padding.medium
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            guide.categories.take(3).forEach {
                CategoryCard(
                    name = it.name,
                    modifier = Modifier.padding(end = GoodzikTheme.padding.average)
                )
            }
        }
    }
}
