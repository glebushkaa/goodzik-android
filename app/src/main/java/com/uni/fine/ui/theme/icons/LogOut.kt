package com.uni.fine.ui.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val LogOut: ImageVector
    get() {
        if (_logOut != null) {
            return _logOut!!
        }
        _logOut = ImageVector.Builder(
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
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(4f, 12f)
                curveTo(4f, 12.2652f, 4.1054f, 12.5196f, 4.2929f, 12.7071f)
                curveTo(4.4804f, 12.8946f, 4.7348f, 13f, 5f, 13f)
                horizontalLineTo(12.59f)
                lineTo(10.29f, 15.29f)
                curveTo(10.1963f, 15.383f, 10.1219f, 15.4936f, 10.0711f, 15.6154f)
                curveTo(10.0203f, 15.7373f, 9.9942f, 15.868f, 9.9942f, 16f)
                curveTo(9.9942f, 16.132f, 10.0203f, 16.2627f, 10.0711f, 16.3846f)
                curveTo(10.1219f, 16.5064f, 10.1963f, 16.617f, 10.29f, 16.71f)
                curveTo(10.383f, 16.8037f, 10.4936f, 16.8781f, 10.6154f, 16.9289f)
                curveTo(10.7373f, 16.9797f, 10.868f, 17.0058f, 11f, 17.0058f)
                curveTo(11.132f, 17.0058f, 11.2627f, 16.9797f, 11.3846f, 16.9289f)
                curveTo(11.5064f, 16.8781f, 11.617f, 16.8037f, 11.71f, 16.71f)
                lineTo(15.71f, 12.71f)
                curveTo(15.801f, 12.6149f, 15.8724f, 12.5028f, 15.92f, 12.38f)
                curveTo(16.02f, 12.1365f, 16.02f, 11.8635f, 15.92f, 11.62f)
                curveTo(15.8724f, 11.4972f, 15.801f, 11.3851f, 15.71f, 11.29f)
                lineTo(11.71f, 7.29f)
                curveTo(11.6168f, 7.1968f, 11.5061f, 7.1228f, 11.3842f, 7.0723f)
                curveTo(11.2624f, 7.0219f, 11.1319f, 6.9959f, 11f, 6.9959f)
                curveTo(10.8681f, 6.9959f, 10.7376f, 7.0219f, 10.6158f, 7.0723f)
                curveTo(10.4939f, 7.1228f, 10.3832f, 7.1968f, 10.29f, 7.29f)
                curveTo(10.1968f, 7.3832f, 10.1228f, 7.4939f, 10.0723f, 7.6158f)
                curveTo(10.0219f, 7.7376f, 9.9959f, 7.8681f, 9.9959f, 8f)
                curveTo(9.9959f, 8.1319f, 10.0219f, 8.2624f, 10.0723f, 8.3842f)
                curveTo(10.1228f, 8.5061f, 10.1968f, 8.6168f, 10.29f, 8.71f)
                lineTo(12.59f, 11f)
                horizontalLineTo(5f)
                curveTo(4.7348f, 11f, 4.4804f, 11.1054f, 4.2929f, 11.2929f)
                curveTo(4.1054f, 11.4804f, 4f, 11.7348f, 4f, 12f)
                close()
                moveTo(17f, 2f)
                horizontalLineTo(7f)
                curveTo(6.2043f, 2f, 5.4413f, 2.3161f, 4.8787f, 2.8787f)
                curveTo(4.3161f, 3.4413f, 4f, 4.2043f, 4f, 5f)
                verticalLineTo(8f)
                curveTo(4f, 8.2652f, 4.1054f, 8.5196f, 4.2929f, 8.7071f)
                curveTo(4.4804f, 8.8946f, 4.7348f, 9f, 5f, 9f)
                curveTo(5.2652f, 9f, 5.5196f, 8.8946f, 5.7071f, 8.7071f)
                curveTo(5.8946f, 8.5196f, 6f, 8.2652f, 6f, 8f)
                verticalLineTo(5f)
                curveTo(6f, 4.7348f, 6.1054f, 4.4804f, 6.2929f, 4.2929f)
                curveTo(6.4804f, 4.1054f, 6.7348f, 4f, 7f, 4f)
                horizontalLineTo(17f)
                curveTo(17.2652f, 4f, 17.5196f, 4.1054f, 17.7071f, 4.2929f)
                curveTo(17.8946f, 4.4804f, 18f, 4.7348f, 18f, 5f)
                verticalLineTo(19f)
                curveTo(18f, 19.2652f, 17.8946f, 19.5196f, 17.7071f, 19.7071f)
                curveTo(17.5196f, 19.8946f, 17.2652f, 20f, 17f, 20f)
                horizontalLineTo(7f)
                curveTo(6.7348f, 20f, 6.4804f, 19.8946f, 6.2929f, 19.7071f)
                curveTo(6.1054f, 19.5196f, 6f, 19.2652f, 6f, 19f)
                verticalLineTo(16f)
                curveTo(6f, 15.7348f, 5.8946f, 15.4804f, 5.7071f, 15.2929f)
                curveTo(5.5196f, 15.1054f, 5.2652f, 15f, 5f, 15f)
                curveTo(4.7348f, 15f, 4.4804f, 15.1054f, 4.2929f, 15.2929f)
                curveTo(4.1054f, 15.4804f, 4f, 15.7348f, 4f, 16f)
                verticalLineTo(19f)
                curveTo(4f, 19.7956f, 4.3161f, 20.5587f, 4.8787f, 21.1213f)
                curveTo(5.4413f, 21.6839f, 6.2043f, 22f, 7f, 22f)
                horizontalLineTo(17f)
                curveTo(17.7956f, 22f, 18.5587f, 21.6839f, 19.1213f, 21.1213f)
                curveTo(19.6839f, 20.5587f, 20f, 19.7956f, 20f, 19f)
                verticalLineTo(5f)
                curveTo(20f, 4.2043f, 19.6839f, 3.4413f, 19.1213f, 2.8787f)
                curveTo(18.5587f, 2.3161f, 17.7956f, 2f, 17f, 2f)
                close()
            }
        }.build()
        return _logOut!!
    }

private var _logOut: ImageVector? = null