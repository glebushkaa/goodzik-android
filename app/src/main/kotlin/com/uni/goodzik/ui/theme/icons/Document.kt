package com.uni.goodzik.ui.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Document: ImageVector
    get() {
        if (_Document != null) {
            return _Document!!
        }
        _Document = ImageVector.Builder(
            name = "Documents",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(19.53f, 9.44f)
                lineTo(12.53f, 2.44f)
                curveTo(12.3827f, 2.3137f, 12.194f, 2.2461f, 12f, 2.25f)
                horizontalLineTo(9f)
                curveTo(6.3766f, 2.25f, 4.25f, 4.3766f, 4.25f, 7f)
                verticalLineTo(17f)
                curveTo(4.25f, 19.6234f, 6.3766f, 21.75f, 9f, 21.75f)
                horizontalLineTo(15f)
                curveTo(17.6234f, 21.75f, 19.75f, 19.6234f, 19.75f, 17f)
                verticalLineTo(10f)
                curveTo(19.7534f, 9.7916f, 19.6743f, 9.5903f, 19.53f, 9.44f)
                close()
                moveTo(12.75f, 4.79f)
                lineTo(17.21f, 9.25f)
                horizontalLineTo(14f)
                curveTo(13.3096f, 9.25f, 12.75f, 8.6904f, 12.75f, 8f)
                verticalLineTo(4.79f)
                close()
                moveTo(5.75f, 17f)
                curveTo(5.7555f, 18.7926f, 7.2073f, 20.2445f, 9f, 20.25f)
                horizontalLineTo(15f)
                curveTo(16.7926f, 20.2445f, 18.2445f, 18.7926f, 18.25f, 17f)
                verticalLineTo(10.75f)
                horizontalLineTo(14f)
                curveTo(12.4812f, 10.75f, 11.25f, 9.5188f, 11.25f, 8f)
                verticalLineTo(3.75f)
                horizontalLineTo(9f)
                curveTo(7.2073f, 3.7555f, 5.7555f, 5.2073f, 5.75f, 7f)
                verticalLineTo(17f)
                close()
            }
        }.build()
        return _Document!!
    }

private var _Document: ImageVector? = null
