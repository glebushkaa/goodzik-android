@file:OptIn(ExperimentalMaterial3Api::class)

package com.uni.fine.ui.screens.info

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.fine.R
import com.uni.fine.model.CheckInfo
import com.uni.fine.model.IssueType
import com.uni.fine.ui.core.component.Screen
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.core.extension.thinBorder
import com.uni.fine.ui.core.extension.verticalScrollbar
import com.uni.fine.ui.theme.UniFineTheme
import com.uni.fine.ui.theme.icons.Cross
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
                onClose = onClose
            )
            if (state.selectedIssue != null) {
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
    onClose: () -> Unit
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
        Spacer(modifier = Modifier.height(UniFineTheme.padding.massive))
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
        Text(
            text = issue?.type.toString(), // TODO Replace with mapping
            style = UniFineTheme.typography.hint,
            modifier = Modifier
                .background(
                    color = UniFineTheme.colors.red,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(UniFineTheme.padding.small),
            color = UniFineTheme.colors.white
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.medium))
        Text(
            text = issue?.message.toString(),
            style = UniFineTheme.typography.fieldText,
            color = UniFineTheme.colors.gray
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.large))
        Text(
            text = "Fixed",
            style = UniFineTheme.typography.hint,
            modifier = Modifier
                .background(
                    color = UniFineTheme.colors.green,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(UniFineTheme.padding.small),
            color = UniFineTheme.colors.white
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
        addStyle(
            style = SpanStyle(
                background = UniFineTheme.colors.amber.copy(alpha = 0.2f),
                color = UniFineTheme.colors.amber,
                textDecoration = TextDecoration.Underline
            ),
            start = issue.start,
            end = issue.end
        )
        addLink(
            clickable = LinkAnnotation.Clickable(
                tag = issue.id,
                linkInteractionListener = { linkAnnotation ->
                    onClick(issue.id)
                }
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
                        id = "dasdas",
                        type = IssueType.Grammar,
                        start = 0,
                        end = 8,
                        text = "SoftServe",
                        message = "SoftServe is a global digital consulting and software development company that’s been around since 1993.",
                        suggestion = "Soft Serve"
                    )
                ),
                text = "SoftServe is a global digital consulting and software development company that’s been around since 1993. With headquarters in Austin, Texas, and Lviv, Ukraine, the company helps businesses of all sizes embrace new technologies and transform the way they operate. By focusing on innovation, scalability, and optimization, SoftServe delivers tailored solutions using cloud technology, data insights, AI, and custom software. \n" +
                        "\n" +
                        "SoftServe helps companies adapt to modern tech trends, making their processes more efficient and competitive. Whether it’s moving to the cloud, optimizing existing setups, or building cloud-native applications, SoftServe has you covered across platforms like AWS, Azure, and Google Cloud. From smarter decision-making to automated workflows, SoftServe builds AI-driven tools that deliver real-world results. SoftServe helps businesses unlock the value in their data, turning numbers into actionable insights that drive growth. They build software that fits your specific needs, using agile methods to ensure fast and effective delivery. Keeping businesses secure is a priority. SoftServe offers advanced assessments and strategies to safeguard data and systems. \n" +
                        "\n" +
                        "SoftServe works with a variety of industries, including healthcare, retail, finance, energy, and manufacturing. They design solutions tailored to each industry’s unique challenges, helping businesses achieve their goals. \n" +
                        "\n" +
                        "With over 13,000 team members spread across North America, Europe, and Asia, SoftServe combines global knowledge with local expertise. They’re known for their innovative approach, collaborative culture, and strong partnerships with tech giants like Microsoft, AWS, and Google. \n" +
                        "\n" +
                        "SoftServe’s work doesn’t go unnoticed—they’ve earned a reputation for their forward-thinking solutions, strong workplace culture, and impactful contributions to the tech industry. In short, SoftServe is more than just a tech company—it’s a partner that helps businesses thrive in today’s fast-paced digital world."
            ),
            onIssueClick = {},
            onClose = {}
        )
    }
}