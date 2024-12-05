package com.uni.goodzik.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.goodzik.R
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.component.UniTextField
import com.uni.goodzik.ui.core.extension.applyIf
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.core.extension.convertLocalDateTimeToUkrainianFormat
import com.uni.goodzik.ui.core.extension.thinBorder
import com.uni.goodzik.ui.theme.GoodzikTheme
import com.uni.goodzik.ui.theme.icons.HidePassword
import com.uni.goodzik.ui.theme.icons.ShowPassword

@Composable
fun ChatScreen(id: String) {
    Screen<ChatViewModel, ChatViewModel.Factory>(
        creationCallback = { factory -> factory.create(id) }
    ) { viewModel ->
        val state by viewModel.state.collectAsStateWithLifecycle()

        ChatScreenContent(
            state = state,
            onMessageChanged = { viewModel.sendAction(ChatAction.MessageChanged(it)) },
            onSend = { viewModel.sendAction(ChatAction.Send) }
        )
    }
}

@Composable
private fun ChatScreenContent(
    state: ChatState,
    onMessageChanged: (String) -> Unit,
    onSend: () -> Unit
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
            text = stringResource(R.string.chat),
            style = GoodzikTheme.typography.heading,
            color = GoodzikTheme.colors.black
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(GoodzikTheme.padding.medium)
        ) {
            items(
                items = state.messages,
                key = { message -> message.id }
            ) { message ->
                Message(
                    modifier = Modifier
                        .applyIf(!message.incoming) {
                            align(Alignment.End).padding(start = GoodzikTheme.padding.enormous)
                        }
                        .applyIf(message.incoming) {
                            align(Alignment.Start).padding(end = GoodzikTheme.padding.enormous)
                        },
                    message = message.text,
                    time = message.time.convertLocalDateTimeToUkrainianFormat(),
                    incoming = message.incoming
                )
            }
        }
        MessageTextField(
            modifier = Modifier.padding(
                vertical = GoodzikTheme.padding.huge
            ),
            text = state.text,
            onTextChange = onMessageChanged,
            onSend = onSend
        )
    }
}

@Composable
private fun Message(
    modifier: Modifier = Modifier,
    message: String,
    time: String,
    incoming: Boolean
) {
    val textColor = if (incoming) GoodzikTheme.colors.pureBlack else GoodzikTheme.colors.snow

    Row {
        if (!incoming) Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = modifier
                .widthIn(min = 160.dp)
                .background(
                    color = if (incoming) GoodzikTheme.colors.lightGray else GoodzikTheme.colors.black,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(
                    vertical = GoodzikTheme.padding.medium,
                    horizontal = GoodzikTheme.padding.average
                ),
        ) {
            Text(
                text = message,
                style = GoodzikTheme.typography.fieldTitle,
                color = textColor
            )
            Spacer(modifier = Modifier.height(GoodzikTheme.padding.small))
            Text(
                modifier = Modifier.align(Alignment.End),
                text = time,
                style = GoodzikTheme.typography.hint,
                color = textColor.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun MessageTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Row(
        modifier = modifier.padding(
            vertical = GoodzikTheme.padding.medium,
            horizontal = GoodzikTheme.padding.average
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            modifier = Modifier.weight(1f),
            text = text,
            maxLines = 5,
            onTextChange = onTextChange,
            hint = stringResource(R.string.write_message),
        )
        Spacer(modifier = Modifier.width(GoodzikTheme.padding.average))
        Icon(
            modifier = Modifier
                .background(
                    color = GoodzikTheme.colors.black,
                    shape = CircleShape
                )
                .clickableNoRipple(onSend)
                .padding(GoodzikTheme.padding.small)
                .size(24.dp),
            tint = GoodzikTheme.colors.snow,
            painter = painterResource(R.drawable.send),
            contentDescription = null,
        )
    }
}

@Composable
private fun TextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    enabled: Boolean = true,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier
            .background(color = GoodzikTheme.colors.lightGray)
            .fillMaxWidth()
            .thinBorder(),
        value = text,
        onValueChange = onTextChange,
        textStyle = GoodzikTheme.typography.fieldText,
        maxLines = maxLines,
        enabled = enabled,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .heightIn(min = 54.dp)
                    .fillMaxWidth()
                    .padding(
                        start = GoodzikTheme.padding.average,
                        end = GoodzikTheme.padding.large
                    )
                    .padding(vertical = GoodzikTheme.padding.medium),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        style = GoodzikTheme.typography.hint,
                        color = GoodzikTheme.colors.gray,
                        maxLines = maxLines,
                    )
                }
                innerTextField()
            }
        }
    )
}