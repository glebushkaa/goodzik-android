package com.uni.fine.ui.screens.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.fine.R
import com.uni.fine.model.CheckStyle
import com.uni.fine.ui.core.component.DropdownScreenContainer
import com.uni.fine.ui.core.component.TopBar
import com.uni.fine.ui.core.component.UniButton
import com.uni.fine.ui.core.component.UniTextField
import com.uni.fine.ui.core.component.rememberBlurState
import com.uni.fine.ui.core.component.rememberDropdownState
import com.uni.fine.ui.core.extension.collectAsEffect
import com.uni.fine.ui.core.extension.verticalScrollbar
import com.uni.fine.ui.theme.UniFineTheme

@Composable
fun CheckSetupScreen() {
    val viewModel = hiltViewModel<CheckSetupViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.sideEffect.collectAsEffect {
        when (it) {
            CheckSetupSideEffect.Next -> TODO()
        }
    }

    CheckSetupContent(
        state = state,
        onTopicChanged = {
            viewModel.sendAction(CheckSetupAction.TopicChanged(it))
        },
        onStyleChanged = {
            viewModel.sendAction(CheckSetupAction.StyleChanged(it))
        },
        onExcludedWordsChanged = {
            viewModel.sendAction(CheckSetupAction.ExcludedWordsChanged(it))
        },
        onNext = {
            viewModel.sendAction(CheckSetupAction.Next)
        }
    )
}

@Composable
private fun CheckSetupContent(
    state: CheckSetupState,
    onTopicChanged: (String) -> Unit,
    onStyleChanged: (CheckStyle) -> Unit,
    onExcludedWordsChanged: (String) -> Unit,
    onNext: () -> Unit
) {
    val dropdownState = rememberDropdownState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = UniFineTheme.padding.huge)
            .imePadding()
    ) {
        TopBar(
            text = stringResource(id = R.string.setup),
        )
        Text(
            text = stringResource(id = R.string.topic),
            style = UniFineTheme.typography.fieldTitle,
            modifier = Modifier
                .padding(top = UniFineTheme.padding.large)
                .padding(top = UniFineTheme.padding.massive)
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.small))
        UniTextField(
            text = state.topic,
            hint = stringResource(id = R.string.topic_hint),
            onTextChange = onTopicChanged
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.large))
        Text(
            text = stringResource(id = R.string.topic),
            style = UniFineTheme.typography.fieldTitle,
            modifier = Modifier.padding(top = UniFineTheme.padding.large)
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.small))
        DropdownScreenContainer(
            dropdownItems = state.styleOptions,
            selectedItemTitle = state.style.name,
            onItemSelected = onStyleChanged,
            state = dropdownState,
            belowDropdownContent = {
                BelowDropdownSection(
                    state = state,
                    onExcludedWordsChanged = onExcludedWordsChanged,
                    onNext = onNext
                )
            },
            blurState = rememberBlurState(),
            dropdownItem = { item, index ->
                CheckStyleDropdownItem(
                    item = item,
                    isDividerVisible = index < state.styleOptions.lastIndex
                )
            }
        )
    }
}

@Composable
private fun CheckStyleDropdownItem(
    item: CheckStyle,
    isDividerVisible: Boolean = true
) {
    Text(
        text = item.name,
        style = UniFineTheme.typography.fieldText,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = UniFineTheme.padding.large,
                vertical = UniFineTheme.padding.medium
            )
    )
    if (isDividerVisible) {
        Spacer(modifier = Modifier.height(UniFineTheme.padding.small))
    }
}

@Composable
private fun BelowDropdownSection(
    state: CheckSetupState,
    onExcludedWordsChanged: (String) -> Unit,
    onNext: () -> Unit,
) {
    val excludedWordsScrollState = rememberScrollState()

    Column {
        Spacer(modifier = Modifier.height(UniFineTheme.padding.large))
        Text(
            text = stringResource(id = R.string.excluded_words),
            style = UniFineTheme.typography.fieldTitle,
            modifier = Modifier.padding(top = UniFineTheme.padding.large)
        )
        Spacer(modifier = Modifier.height(UniFineTheme.padding.small))
        UniTextField(
            modifier = Modifier
                .verticalScroll(excludedWordsScrollState)
                .verticalScrollbar(excludedWordsScrollState)
                .height(120.dp),
            text = state.excludedWords,
            maxLines = Int.MAX_VALUE,
            centered = false,
            hint = stringResource(id = R.string.excluded_words_hint),
            onTextChange = onExcludedWordsChanged
        )
        Spacer(modifier = Modifier.weight(1f))
        UniButton(
            modifier = Modifier.padding(
                horizontal = UniFineTheme.padding.medium,
            ),
            enabled = state.buttonEnabled,
            text = stringResource(id = R.string.next),
            onClick = onNext,
        )
        Spacer(modifier = Modifier.padding(top = UniFineTheme.padding.enormous))
    }
}

@Composable
@Preview
private fun CheckSetupPreview() {
    val state = CheckSetupState(
        topic = "Topic",
        style = CheckStyle.Neutral,
        excludedWords = "Excluded words"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UniFineTheme.colors.white)
    ) {
        CheckSetupContent(
            state = state,
            onTopicChanged = {},
            onStyleChanged = {},
            onExcludedWordsChanged = {},
            onNext = {}
        )
    }
}