package com.uni.fine.ui.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val HidePassword: ImageVector
    get() = ImageVector.Builder(
        name = "CustomIcon",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
        path(
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(12f, 4f)
            curveTo(7f, 4f, 3f, 8f, 1.5f, 12f)
            curveTo(3f, 16f, 7f, 20f, 12f, 20f)
            curveTo(17f, 20f, 21f, 16f, 22.5f, 12f)
            curveTo(21f, 8f, 17f, 4f, 12f, 4f)
        }
        path(
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
            pathFillType = PathFillType.NonZero
        ) {
            moveTo(9f, 12f)
            curveTo(9f, 12.7956f, 9.3161f, 13.5587f, 9.8787f, 14.1213f)
            curveTo(10.4413f, 14.6839f, 11.2044f, 15f, 12f, 15f)
            curveTo(12.7956f, 15f, 13.5587f, 14.6839f, 14.1213f, 14.1213f)
            curveTo(14.6839f, 13.5587f, 15f, 12.7956f, 15f, 12f)
        }
    }.build()
