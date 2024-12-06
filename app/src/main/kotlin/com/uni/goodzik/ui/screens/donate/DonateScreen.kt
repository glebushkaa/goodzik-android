package com.uni.goodzik.ui.screens.donate

import android.graphics.Typeface.MONOSPACE
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.uni.goodzik.R
import com.uni.goodzik.ui.core.component.GoodzikButton
import com.uni.goodzik.ui.core.component.Screen
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.core.extension.openLink
import com.uni.goodzik.ui.theme.GoodzikTheme

@Composable
fun DonateScreen() {
    Screen<DonateViewModel> { viewModel ->
        val toastMessage = stringResource(R.string.card_copied)
        val state by viewModel.state.collectAsStateWithLifecycle()

        DonateScreenContent(
            state = state,
            onCopied = {
                val action = DonateAction.Toast(toastMessage)
                viewModel.sendAction(action)
            }
        )
    }
}

@Composable
private fun DonateScreenContent(
    state: DonateState,
    onCopied: () -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GoodzikTheme.colors.milk)
            .statusBarsPadding()
            .padding(top = GoodzikTheme.padding.huge)
            .padding(horizontal = GoodzikTheme.padding.huge)
    ) {
        Text(
            text = stringResource(R.string.donate),
            style = GoodzikTheme.typography.heading,
            color = GoodzikTheme.colors.black
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.enormous))
        Text(
            modifier = Modifier,
            text = stringResource(R.string.card_number),
            style = GoodzikTheme.typography.fieldText,
            color = GoodzikTheme.colors.black
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.medium))
        Row(
            modifier = Modifier
                .background(
                    color = GoodzikTheme.colors.black.copy(0.1f),
                    shape = RoundedCornerShape(12.dp)
                )
                .height(56.dp)
                .padding(
                    horizontal = GoodzikTheme.padding.large,
                    vertical = GoodzikTheme.padding.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = state.cardNumber,
                style = GoodzikTheme.typography.fieldText.copy(
                    fontFamily = FontFamily(MONOSPACE)
                ),
                color = GoodzikTheme.colors.black
            )
            Spacer(modifier = Modifier.width(GoodzikTheme.padding.large))
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clickableNoRipple {
                        clipboardManager.setText(AnnotatedString(state.cardNumber))
                        onCopied()
                    },
                painter = painterResource(id = R.drawable.copy),
                contentDescription = null,
                tint = GoodzikTheme.colors.ocean
            )
        }
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
        GoodzikButton(
            text = stringResource(R.string.bank_link),
            color = GoodzikTheme.colors.black,
            textColor = GoodzikTheme.colors.snow,
            icon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.monobank_logo),
                    contentDescription = null
                )
            },
            onClick = {
                context.openLink("https://send.monobank.ua/jar/5VV7zhDJGY")
            }
        )
        Spacer(modifier = Modifier.height(GoodzikTheme.padding.large))
        GoodzikButton(
            modifier = Modifier.shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp)
            ),
            text = stringResource(R.string.paypal_link),
            color = GoodzikTheme.colors.snow,
            textColor = GoodzikTheme.colors.black,
            icon = {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.paypal),
                    contentDescription = null
                )
            },
            onClick = {
                context.openLink(
                    "https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&bn=AngellEYE_PHPClass&business=marishka.polo@gmail.com"
                )
            }
        )
    }
}