package com.uni.fine.ui.core.component

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uni.fine.ui.core.extension.applyIf
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.core.extension.thinBorder
import com.uni.fine.ui.theme.UniFineTheme
import com.uni.fine.ui.theme.icons.Cross

enum class TextFieldMode {
    Password, None
}

@Composable
fun UniTextField(
    modifier: Modifier = Modifier,
    innerModifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    enabled: Boolean = true,
    centered: Boolean = true,
    mode: TextFieldMode = TextFieldMode.None,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = 1,
    onReveal: () -> Unit = {},
    onTextChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier
            .heightIn(min = 50.dp)
            .fillMaxWidth()
            .thinBorder(),
        value = text,
        onValueChange = onTextChange,
        textStyle = UniFineTheme.typography.fieldText,
        visualTransformation = visualTransformation,
        maxLines = maxLines,
        enabled = enabled,
        decorationBox = { innerTextField ->
            Row(
                modifier = innerModifier
                    .fillMaxWidth()
                    .padding(
                        start = UniFineTheme.padding.average,
                        end = UniFineTheme.padding.large
                    )
                    .applyIf(!centered) {
                        padding(vertical = UniFineTheme.padding.average)
                    }
                    .graphicsLayer {
                        alpha = if (enabled) 1f else 0.4f
                    },
                verticalAlignment = if (centered) Alignment.CenterVertically else Alignment.Top,
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    if (text.isEmpty()) {
                        Text(
                            text = hint,
                            style = UniFineTheme.typography.hint,
                            color = UniFineTheme.colors.gray,
                            maxLines = maxLines,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

@Composable
@Preview
fun UniTextFieldPreview() {
    UniTextField(
        text = "",
        hint = "Enter email",
        centered = false,
        onTextChange = {},
    )
}