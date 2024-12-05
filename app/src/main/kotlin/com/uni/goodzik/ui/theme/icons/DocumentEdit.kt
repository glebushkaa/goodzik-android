package com.uni.goodzik.ui.theme.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val DocumentEdit: ImageVector
    get() {
        if (_DocumentEdit != null) {
            return _DocumentEdit!!
        }
        _DocumentEdit = ImageVector.Builder(
            name = "DocumentEdit",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 48f,
            viewportHeight = 48f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(13.14f, 22.84f)
                curveTo(14.37f, 23.39f, 15.37f, 24.33f, 16.0f, 25.52f)
                lineTo(21.28f, 35.14f)
                curveTo(21.68f, 35.86f, 21.88f, 36.66f, 21.86f, 37.48f)
                lineTo(21.76f, 41.48f)
                curveTo(21.76f, 42.31f, 21.32f, 43.07f, 20.6f, 43.48f)
                curveTo(20.27f, 43.65f, 19.91f, 43.74f, 19.54f, 43.74f)
                curveTo(19.09f, 43.74f, 18.66f, 43.61f, 18.28f, 43.38f)
                lineTo(14.92f, 41.38f)
                curveTo(14.22f, 40.95f, 13.65f, 40.34f, 13.26f, 39.62f)
                lineTo(8.0f, 30f)
                curveTo(7.34f, 28.77f, 7.11f, 27.35f, 7.36f, 25.98f)
                curveTo(7.51f, 24.68f, 8.25f, 23.53f, 9.36f, 22.84f)
                curveTo(10.55f, 22.25f, 11.95f, 22.25f, 13.14f, 22.84f)
                close()
                moveTo(16.46f, 38.72f)
                lineTo(18.78f, 40.12f)
                lineTo(18.76f, 37.36f)
                curveTo(18.75f, 37.07f, 18.68f, 36.78f, 18.54f, 36.52f)
                lineTo(13.28f, 26.9f)
                curveTo(12.97f, 26.29f, 12.47f, 25.81f, 11.86f, 25.52f)
                curveTo(11.65f, 25.42f, 11.43f, 25.36f, 11.2f, 25.36f)
                curveTo(11.03f, 25.36f, 10.87f, 25.4f, 10.72f, 25.48f)
                curveTo(10.39f, 25.7f, 10.18f, 26.06f, 10.14f, 26.46f)
                curveTo(10.05f, 27.15f, 10.19f, 27.86f, 10.54f, 28.46f)
                lineTo(15.8f, 38f)
                curveTo(15.94f, 38.3f, 16.17f, 38.55f, 16.46f, 38.72f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(26.44f, 4.7f)
                lineTo(40.44f, 18.7f)
                curveTo(40.74f, 18.99f, 40.9f, 19.4f, 40.88f, 19.82f)
                verticalLineTo(33.82f)
                curveTo(40.88f, 36.34f, 39.88f, 38.76f, 38.1f, 40.54f)
                curveTo(36.32f, 42.32f, 33.9f, 43.32f, 31.38f, 43.32f)
                horizontalLineTo(25.64f)
                curveTo(24.81f, 43.32f, 24.14f, 42.65f, 24.14f, 41.82f)
                curveTo(24.14f, 40.99f, 24.81f, 40.32f, 25.64f, 40.32f)
                horizontalLineTo(31.38f)
                curveTo(34.97f, 40.31f, 37.87f, 37.41f, 37.88f, 33.82f)
                verticalLineTo(21.32f)
                horizontalLineTo(29.38f)
                curveTo(26.35f, 21.31f, 23.89f, 18.85f, 23.88f, 15.82f)
                verticalLineTo(7.32f)
                horizontalLineTo(19.38f)
                curveTo(15.79f, 7.32f, 12.88f, 10.23f, 12.88f, 13.82f)
                verticalLineTo(19.12f)
                curveTo(12.88f, 19.95f, 12.21f, 20.62f, 11.38f, 20.62f)
                curveTo(10.55f, 20.62f, 9.88f, 19.95f, 9.88f, 19.12f)
                verticalLineTo(13.82f)
                curveTo(9.86f, 11.29f, 10.86f, 8.86f, 12.64f, 7.06f)
                curveTo(14.42f, 5.27f, 16.85f, 4.26f, 19.38f, 4.26f)
                horizontalLineTo(25.38f)
                curveTo(25.78f, 4.26f, 26.16f, 4.42f, 26.44f, 4.7f)
                close()
                moveTo(26.88f, 9.38f)
                verticalLineTo(15.82f)
                curveTo(26.88f, 17.2f, 28.0f, 18.32f, 29.38f, 18.32f)
                horizontalLineTo(35.82f)
                lineTo(26.88f, 9.38f)
                close()
            }
        }.build()
        return _DocumentEdit!!
    }

private var _DocumentEdit: ImageVector? = null
