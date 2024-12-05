package com.uni.goodzik.ui.core.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.uni.goodzik.ui.core.extension.clickableNoRipple
import com.uni.goodzik.ui.core.extension.thinBorder
import com.uni.goodzik.ui.theme.UniFineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
inline fun <reified T> DropdownScreenContainer(
    state: DropdownState = rememberDropdownState(),
    selectedItemTitle: String,
    dropdownItems: ImmutableList<T>,
    noinline onItemSelected: (T) -> Unit,
    noinline dropdownItem: @Composable (item: T, index: Int) -> Unit,
    noinline belowDropdownContent: @Composable (() -> Unit)? = null,
) {
    val itemsIsEmpty by remember(dropdownItems) {
        derivedStateOf { dropdownItems.isEmpty() }
    }
    var resultPadding by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    Box {
        belowDropdownContent?.let {
            Box(modifier = Modifier.padding(top = resultPadding)) {
                it.invoke()
            }
        }
        Box(modifier = Modifier.thinBorder()) {
            CustomBuildDropDown(
                modifier = Modifier.padding(top = resultPadding),
                expanded = state.visible,
                list = dropdownItems,
                onItemClick = {
                    scope.launch {
                        state.hide()
                        onItemSelected(it)
                    }
                }
            ) { index, company ->
                dropdownItem(company, index)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged {
                        resultPadding = with(density) { it.height.toDp() }
                    }
            ) {
                SelectedDropdownItem(
                    modifier = Modifier.clickableNoRipple {
                        if (dropdownItems.isNotEmpty()) {
                            if (state.visible) state.hide() else state.show()
                        }
                    },
                    text = selectedItemTitle,
                    arrowVisible = !itemsIsEmpty,
                    isMenuVisible = state.visible,
                )
            }
        }
    }
}

@Composable
inline fun <reified T> CustomBuildDropDown(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    list: ImmutableList<T>,
    noinline onItemClick: (T) -> Unit,
    noinline content: @Composable BoxScope.(index: Int, item: T) -> Unit,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = expanded,
        enter = expandVertically(
            animationSpec = tween(DROPDOWN_ANIMATION_DURATION),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(DROPDOWN_ANIMATION_DURATION),
            shrinkTowards = Alignment.Top
        )
    ) {
        Column(
            modifier = Modifier
                .background(UniFineTheme.colors.white)
                .fillMaxWidth(),
            content = {
                list.forEachIndexed { index, item ->
                    Box(
                        modifier = Modifier.clickableNoRipple {
                            onItemClick(item)
                        }
                    ) {
                        content(index, item)
                    }
                }
            }
        )
    }
}

@Composable
fun SelectedDropdownItem(
    text: String,
    arrowVisible: Boolean,
    isMenuVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val angle by animateFloatAsState(targetValue = if (isMenuVisible) 180f else 0f, label = "")

    Row(
        modifier = modifier
            .thinBorder()
            .heightIn(min = 50.dp)
            .fillMaxWidth()
            .background(UniFineTheme.colors.white)
            .padding(
                start = UniFineTheme.padding.average,
                end = UniFineTheme.padding.large
            )
            .padding(vertical = UniFineTheme.padding.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            color = UniFineTheme.colors.black,
            style = UniFineTheme.typography.fieldText
        )
        if (arrowVisible) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer {
                        rotationZ = angle
                    },
                tint = UniFineTheme.colors.black.copy(alpha = 0.3f)
            )
        }
    }
}

class DropdownState(
    visible: Boolean = false,
) {
    var visible by mutableStateOf(visible)
        private set

    fun show() {
        visible = true
    }

    fun hide() {
        visible = false
    }

    companion object {
        private const val VISIBLE_KEY = "visible"
        val saver = mapSaver(
            save = {
                mapOf(VISIBLE_KEY to it.visible)
            },
            restore = {
                DropdownState(it[VISIBLE_KEY] as Boolean)
            }
        )
    }
}

@Composable
fun rememberDropdownState(): DropdownState {
    return rememberSaveable(saver = DropdownState.saver) {
        DropdownState()
    }
}

const val DROPDOWN_ANIMATION_DURATION = 400