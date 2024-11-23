package com.uni.fine.ui.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
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
    textStyle: TextStyle = UniFineTheme.typography.heading
) {
    Row(
        modifier = modifier
            .background(color = UniFineTheme.colors.white)
            .statusBarsPadding()
            .padding(top = UniFineTheme.padding.enormous),
    ) {
        Text(
            text = text,
            style = textStyle,
        )
    }
}