package com.uni.fine.ui.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Plus: ImageVector
    get() {
        if (_plus != null) {
            return _plus!!
        }
        _plus = ImageVector.Builder(
            name = "PlusIcon",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                stroke = SolidColor(Color(0xFF000000)),
                strokeLineWidth = 2f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 2f)
                lineTo(12f, 22f)
                moveTo(2f, 12f)
                lineTo(22f, 12f)
            }
        }.build()
        return _plus!!
    }

private var _plus: ImageVector? = null
