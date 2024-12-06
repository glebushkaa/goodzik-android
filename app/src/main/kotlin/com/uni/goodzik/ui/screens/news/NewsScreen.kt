package com.uni.goodzik.ui.screens.news

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.goodzik.R
import com.uni.goodzik.model.News
import com.uni.goodzik.ui.core.component.CategoryCard
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.applyIf
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.core.extension.convertLocalDateTimeToUkrainianFormat
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun NewsScreen(
    onNewsClick: (id: String) -> Unit
) {
    Screen<NewsViewModel> { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        NewsScreenContent(
            state = state,
            onNewsClick = onNewsClick
        )
    }
}

@Composable
private fun NewsScreenContent(
    state: NewsState,
    onNewsClick: (id: String) -> Unit
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
            text = stringResource(R.string.news),
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
                items = state.news,
                key = { news -> news.id }
            ) { news ->
                val isLast = news.id == state.news.lastOrNull()?.id
                NewsCard(
                    modifier = Modifier
                        .animateItem()
                        .applyIf(isLast) { navigationBarsPadding() },
                    news = news
                ) {
                    onNewsClick(news.id)
                }
            }
        }
    }
}

@Composable
private fun NewsCard(
    modifier: Modifier = Modifier,
    news: News,
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
            Icon(
                painter = painterResource(id = R.drawable.news_item),
                contentDescription = null,
                tint = GoodzikTheme.colors.purple,
                modifier = Modifier.size(54.dp)
            )
            Spacer(modifier = Modifier.width(GoodzikTheme.padding.average))
            Column(
                modifier = Modifier
                    .padding(end = GoodzikTheme.padding.average)
                    .weight(1f)
            ) {
                Text(
                    text = news.author,
                    maxLines = 1,
                    style = GoodzikTheme.typography.body,
                    color = GoodzikTheme.colors.black,
                )
                Spacer(modifier = Modifier.height(GoodzikTheme.padding.average))
                Text(
                    text = news.date.convertLocalDateTimeToUkrainianFormat(),
                    style = GoodzikTheme.typography.hint,
                )
            }
        }
        Text(
            text = news.description,
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
            news.categories.take(3).forEach {
                CategoryCard(
                    name = it.name,
                    modifier = Modifier.padding(end = GoodzikTheme.padding.average)
                )
            }
        }
    }
}