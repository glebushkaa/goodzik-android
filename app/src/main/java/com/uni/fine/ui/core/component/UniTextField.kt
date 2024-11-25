package com.uni.fine.ui.core.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uni.fine.ui.core.extension.applyIf
import com.uni.fine.ui.theme.UniFineTheme

@Composable
fun UniTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String = "",
    centered: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier
            .heightIn(min = 50.dp)
            .border(
                width = 1.dp,
                color = UniFineTheme.colors.black,
                shape = RoundedCornerShape(10.dp)
            ),
        value = text,
        onValueChange = onTextChange,
        textStyle = UniFineTheme.typography.fieldText,
        visualTransformation = visualTransformation,
        maxLines = maxLines,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = UniFineTheme.padding.average,
                        end = UniFineTheme.padding.large
                    )
                    .applyIf(!centered) {
                        padding(vertical = UniFineTheme.padding.medium)
                    },
                contentAlignment = if (centered) Alignment.CenterStart else Alignment.TopStart,
            ) {
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