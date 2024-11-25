package com.uni.fine.ui.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Cross: ImageVector
    get() {
        if (_Cross != null) {
            return _Cross!!
        }
        _Cross = ImageVector.Builder(
            name = "Outline",
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
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(13.41f, 12f)
                lineTo(19.71f, 5.71f)
                curveTo(19.8982f, 5.5217f, 20.004f, 5.2663f, 20.004f, 5f)
                curveTo(20.004f, 4.7337f, 19.8982f, 4.4783f, 19.7099f, 4.29f)
                curveTo(19.5216f, 4.1017f, 19.2662f, 3.9959f, 19f, 3.9959f)
                curveTo(18.7336f, 3.9959f, 18.4782f, 4.1017f, 18.2899f, 4.29f)
                lineTo(12f, 10.59f)
                lineTo(5.71f, 4.29f)
                curveTo(5.5216f, 4.1017f, 5.2662f, 3.9959f, 5f, 3.9959f)
                curveTo(4.7336f, 3.9959f, 4.4782f, 4.1017f, 4.2899f, 4.29f)
                curveTo(4.1016f, 4.4783f, 3.9958f, 4.7337f, 3.9958f, 5f)
                curveTo(3.9958f, 5.2663f, 4.1016f, 5.5217f, 4.2899f, 5.71f)
                lineTo(10.59f, 12f)
                lineTo(4.29f, 18.29f)
                curveTo(4.1962f, 18.383f, 4.1218f, 18.4936f, 4.071f, 18.6154f)
                curveTo(4.0203f, 18.7373f, 3.9941f, 18.868f, 3.9941f, 19f)
                curveTo(3.9941f, 19.132f, 4.0203f, 19.2627f, 4.071f, 19.3846f)
                curveTo(4.1218f, 19.5064f, 4.1962f, 19.617f, 4.2899f, 19.71f)
                curveTo(4.3829f, 19.8037f, 4.4935f, 19.8781f, 4.6154f, 19.9289f)
                curveTo(4.7372f, 19.9797f, 4.8679f, 20.0058f, 5f, 20.0058f)
                curveTo(5.132f, 20.0058f, 5.2627f, 19.9797f, 5.3845f, 19.9289f)
                curveTo(5.5064f, 19.8781f, 5.617f, 19.8037f, 5.7099f, 19.71f)
                lineTo(12f, 13.41f)
                lineTo(18.29f, 19.71f)
                curveTo(18.3829f, 19.8037f, 18.4935f, 19.8781f, 18.6154f, 19.9289f)
                curveTo(18.7372f, 19.9797f, 18.8679f, 20.0058f, 19f, 20.0058f)
                curveTo(19.132f, 20.0058f, 19.2627f, 19.9797f, 19.3845f, 19.9289f)
                curveTo(19.5064f, 19.8781f, 19.617f, 19.8037f, 19.7099f, 19.71f)
                curveTo(19.8037f, 19.617f, 19.8781f, 19.5064f, 19.9288f, 19.3846f)
                curveTo(19.9796f, 19.2627f, 20.0057f, 19.132f, 20.0057f, 19f)
                curveTo(20.0057f, 18.868f, 19.9796f, 18.7373f, 19.9288f, 18.6154f)
                curveTo(19.8781f, 18.4936f, 19.8037f, 18.383f, 19.7099f, 18.29f)
                lineTo(13.41f, 12f)
                close()
            }
        }.build()
        return _Cross!!
    }

private var _Cross: ImageVector? = null
