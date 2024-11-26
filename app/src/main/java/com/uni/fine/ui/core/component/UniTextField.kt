package com.uni.fine.ui.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uni.fine.ui.core.extension.applyIf
import com.uni.fine.ui.core.extension.clickableNoRipple
import com.uni.fine.ui.core.extension.thinBorder
import com.uni.fine.ui.theme.UniFineTheme
import com.uni.fine.ui.theme.icons.HidePassword
import com.uni.fine.ui.theme.icons.ShowPassword

@Composable
fun UniTextField(
    modifier: Modifier = Modifier,
    innerModifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    enabled: Boolean = true,
    centered: Boolean = true,
    isPassword: Boolean = false,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
) {
    var revealed by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier
            .heightIn(min = 50.dp)
            .fillMaxWidth()
            .thinBorder(),
        value = text,
        onValueChange = onTextChange,
        textStyle = UniFineTheme.typography.fieldText,
        visualTransformation = if (isPassword && !revealed) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
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
                            modifier = Modifier.align(Alignment.CenterStart),
                            text = hint,
                            style = UniFineTheme.typography.hint,
                            color = UniFineTheme.colors.gray,
                            maxLines = maxLines,
                        )
                    }
                    innerTextField()
                }
                if (isPassword) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickableNoRipple { revealed = !revealed },
                        imageVector = if (revealed) HidePassword else ShowPassword,
                        contentDescription = null
                    )
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