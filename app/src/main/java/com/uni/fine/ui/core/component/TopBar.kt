package com.uni.fine.ui.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.uni.fine.ui.theme.UniFineTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = UniFineTheme.typography.heading,
) {
    Text(
        modifier = modifier
            .statusBarsPadding()
            .padding(top = UniFineTheme.padding.enormous)
            .padding(end = UniFineTheme.padding.average),
        text = text,
        style = textStyle,
    )
}