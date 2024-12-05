package com.uni.goodzik.ui.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = GoodzikTheme.typography.heading,
) {
    Text(
        modifier = modifier
            .statusBarsPadding()
            .padding(top = GoodzikTheme.padding.enormous)
            .padding(end = GoodzikTheme.padding.average),
        text = text,
        style = textStyle,
    )
}