@file:OptIn(ExperimentalMaterial3Api::class)

package com.uni.fine.ui.screens.info

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.fine.R
import com.uni.fine.model.CheckInfo
import com.uni.fine.model.IssueType
import com.uni.fine.model.Plagiarism
import com.uni.fine.ui.core.component.Screen
import com.uni.fine.ui.core.component.TypeChip
import com.uni.fine.ui.core.component.UniButton
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.core.extension.openLink
import com.uni.fine.ui.core.extension.thinBorder
import com.uni.fine.ui.core.extension.toIssueColor
import com.uni.fine.ui.core.extension.toPlagiarismColor
import com.uni.fine.ui.core.extension.verticalScrollbar
import com.uni.fine.ui.theme.UniFineTheme
import com.uni.fine.ui.theme.icons.Cross
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun InfoScreen(
    id: String,
    new: Boolean,
    onClose: () -> Unit
) {
    Screen<InfoViewModel, InfoViewModel.Factory>(
        creationCallback = { factory -> factory.create(id, new) }
    ) { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()
        val sheetState = rememberModalBottomSheetState()

        Box {
            InfoScreenContent(
                state = state,
                onIssueClick = { issueId ->
                    viewModel.sendAction(InfoAction.IssueClicked(issueId))
                },
                onClose = onClose,
                onPlagiarismRequest = {
                    viewModel.sendAction(InfoAction.RequestMatches)
                }
            )
            when (state.sheet) {
                InfoViewModel.Sheet.Issue -> {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = {
                            viewModel.sendAction(InfoAction.HideIssue)
                        },
                        content = {
                            IssueBottomSheet(state.selectedIssue)
                        }
                    )
                }

                InfoViewModel.Sheet.Plagiarism -> {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = {
                            viewModel.sendAction(InfoAction.HideIssue)
                        },
                        content = {
                            PlagiarismBottomSheet(state.plagiarisms)
                        }
                    )
                }

                null -> {}
            }
        }

        LaunchedEffect(state.selectedIssue) {
            if (state.selectedIssue != null) sheetState.show() else sheetState.hide()
        }
    }
}

@Composable
private fun InfoScreenContent(
    state: InfoState,
    onIssueClick: (String) -> Unit,
    onClose: () -> Unit,
    onPlagiarismRequest: () -> Unit
) {
    val textScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(top = UniFineTheme.padding.enormous)
            .padding(end = UniFineTheme.padding.average)
            .padding(horizontal = UniFineTheme.padding.huge)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.summary),
                style = UniFineTheme.typography.heading,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Cross,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickableNoRipple(onClose)
            )
        }
        Spacer(modifier = Modifier.height(UniFineTheme.padding.large))
        Text(
            modifier = Modifier,
            text = "AI generated probability is ${state.aiScore}%",
            style = UniFineTheme.typography.body,
            textDecoration = TextDecoration.Underline
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.huge))
        Text(
            modifier = Modifier
                .thinBorder()
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = UniFineTheme.padding.large)
                .verticalScroll(textScrollState)
                .verticalScrollbar(
                    scrollState = textScrollState,
                    indicatorSize = DpSize(4.dp, 40.dp)
                )
                .padding(horizontal = UniFineTheme.padding.large),
            text = buildAnnotatedIssuesText(text = state.text, issues = state.issues) {
                onIssueClick(it)
            },
            style = UniFineTheme.typography.fieldText,
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.enormous))
        AnimatedContent(
            modifier = Modifier
                .height(54.dp)
                .padding(horizontal = UniFineTheme.padding.medium),
            targetState = state.buttonVisible,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = ""
        ) {
            if (it) {
                UniButton(
                    loading = state.buttonLoading,
                    text = stringResource(id = R.string.plagiarism_check),
                    onClick = onPlagiarismRequest,
                )
            } else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.no_matches),
                    style = UniFineTheme.typography.hugeBody,
                    color = UniFineTheme.colors.black
                )
            }
        }
        Spacer(modifier = Modifier.height(UniFineTheme.padding.enormous))
    }
}

@Composable
private fun PlagiarismBottomSheet(
    plagiarisms: ImmutableList<Plagiarism> = persistentListOf()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(horizontal = UniFineTheme.padding.huge)
            .padding(bottom = UniFineTheme.padding.huge)
    ) {
        Text(
            text = "Plagiarism probability",
            style = UniFineTheme.typography.smallHeading,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.huge))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(
                UniFineTheme.padding.average
            ),
            contentPadding = PaddingValues(bottom = UniFineTheme.padding.large)
        ) {
            items(
                items = plagiarisms,
                key = { it.id }
            ) { plagiarism ->
                TypeChip(
                    text = plagiarism.probability.name,
                    color = plagiarism.probability.toPlagiarismColor()
                )
                Spacer(modifier = Modifier.height(UniFineTheme.padding.medium))
                Text(
                    modifier = Modifier.clickableNoRipple {
                        context.openLink(plagiarism.url)
                    },
                    text = plagiarism.url,
                    style = UniFineTheme.typography.fieldText,
                    color = UniFineTheme.colors.ocean,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Composable
private fun IssueBottomSheet(
    issue: CheckInfo.Issue?
) {
    Column(
        modifier = Modifier
            .padding(horizontal = UniFineTheme.padding.huge)
            .padding(bottom = UniFineTheme.padding.huge)
    ) {
        Text(
            text = "Suggestion",
            style = UniFineTheme.typography.smallHeading,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.huge))
        TypeChip(
            text = issue?.type?.name ?: "",
            color = issue?.type?.toIssueColor() ?: UniFineTheme.colors.red
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.medium))
        Text(
            text = issue?.message.toString(),
            style = UniFineTheme.typography.fieldText,
            color = UniFineTheme.colors.gray
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.large))
        TypeChip(
            text = "Fixed",
            color = UniFineTheme.colors.green
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.medium))
        Text(
            text = issue?.suggestion.toString(),
            style = UniFineTheme.typography.fieldText,
            color = UniFineTheme.colors.gray
        )
    }
}

@Composable
private fun buildAnnotatedIssuesText(
    text: String,
    issues: List<CheckInfo.Issue>,
    onClick: (String) -> Unit
) = buildAnnotatedString {
    append(text)
    issues.forEach { issue ->
        if (text.length < issue.end) return@forEach
        addStyle(
            style = SpanStyle(
                background = issue.type.toIssueColor().copy(alpha = 0.2f),
                color = issue.type.toIssueColor(),
                textDecoration = TextDecoration.Underline
            ),
            start = issue.start,
            end = issue.end
        )
        addLink(
            clickable = LinkAnnotation.Clickable(
                tag = issue.id,
                linkInteractionListener = { onClick(issue.id) }
            ),
            start = issue.start,
            end = issue.end
        )
    }
}

@Preview
@Composable
private fun InfoScreenContentPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UniFineTheme.colors.white)
    ) {
        InfoScreenContent(
            state = InfoState(
                issues = persistentListOf(
                    CheckInfo.Issue(
                        id = "1",
                        type = IssueType.Style,
                        text = "World War II[b] or the Second World War",
                        message = "Unnecessary use of the square bracket citation format.",
                        suggestion = "Remove the '[b]' and just use 'World War II' or 'the Second World War'.",
                        start = 657,
                        end = 804
                    ),
                ),
                text = """SoftServe Overview

SoftServe is a leading global IT and consulting company specializing in software development, digital transformation, and IT services. Founded in 1993, the company is headquartered in Austin, Texas, with a strong presence in Europe and offices across the globe, including major hubs in Ukraine, Poland, Bulgaria, and other regions. 

SoftServe offers a wide range of solutions to businesses, enabling them to innovate and improve operational efficiency. Its expertise spans areas such as cloud computing, artificial intelligence, machine learning, cybersecurity, data analytics, Internet of Things (IoT), and customer experience design. By leveraging cutting-edge technologies and deep industry expertise, SoftServe delivers tailored solutions that align with clients' strategic goals.
"""
            ),
            onIssueClick = {},
            onClose = {},
            onPlagiarismRequest = {}
        )
    }
}