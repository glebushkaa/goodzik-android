package com.uni.goodzik.ui.theme.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val Profile: ImageVector
    get() {
        if (_Profile != null) {
            return _Profile!!
        }
        _Profile = ImageVector.Builder(
        name = "Profile",
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
                moveTo(18.7107f, 16.0229f)
                lineTo(18.9231f, 17.0322f)
                curveTo(19.12450f, 17.90470f, 18.92570f, 18.82160f, 18.3810f, 19.53240f)
                curveTo(17.83630f, 20.24320f, 17.00270f, 20.67370f, 16.10780f, 20.70620f)
                horizontalLineTo(7.89216f)
                curveTo(6.99730f, 20.67370f, 6.16360f, 20.24320f, 5.6190f, 19.53240f)
                curveTo(5.07430f, 18.82160f, 4.87550f, 17.90470f, 5.07690f, 17.03220f)
                lineTo(5.28935f, 16.0229f)
                curveTo(5.53370f, 14.65670f, 6.70820f, 13.65270f, 8.09580f, 13.62370f)
                horizontalLineTo(15.9042f)
                curveTo(17.29180f, 13.65270f, 18.46630f, 14.65670f, 18.71070f, 16.02290f)
                close()
                moveTo(16.1078f, 19.3694f)
                curveTo(16.55740f, 19.36420f, 16.98050f, 19.15590f, 17.25870f, 18.80280f)
                verticalLineTo(18.8117f)
                curveTo(17.60020f, 18.38330f, 17.73320f, 17.82520f, 17.62170f, 17.28890f)
                lineTo(17.4092f, 16.2797f)
                curveTo(17.29140f, 15.53170f, 16.66080f, 14.97170f, 15.90420f, 14.94290f)
                horizontalLineTo(8.09578f)
                curveTo(7.33920f, 14.97170f, 6.70860f, 15.53170f, 6.59080f, 16.27970f)
                lineTo(6.37828f, 17.2889f)
                curveTo(6.26950f, 17.82240f, 6.40240f, 18.37670f, 6.74130f, 18.80280f)
                curveTo(7.01950f, 19.15590f, 7.44260f, 19.36420f, 7.89220f, 19.36940f)
                horizontalLineTo(16.1078f)
                close()
            }
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
                moveTo(12.4426f, 11.8531f)
                horizontalLineTo(11.5573f)
                curveTo(9.60160f, 11.85310f, 8.01610f, 10.26760f, 8.01610f, 8.31190f)
                verticalLineTo(5.97466f)
                curveTo(8.01370f, 5.1850f, 8.32640f, 4.4270f, 8.88470f, 3.86870f)
                curveTo(9.44310f, 3.31030f, 10.20110f, 2.99770f, 10.99070f, 30f)
                horizontalLineTo(13.0092f)
                curveTo(13.79890f, 2.99770f, 14.55690f, 3.31030f, 15.11520f, 3.86870f)
                curveTo(15.67360f, 4.4270f, 15.98620f, 5.1850f, 15.98390f, 5.97470f)
                verticalLineTo(8.31187f)
                curveTo(15.98390f, 10.26760f, 14.39840f, 11.85310f, 12.44260f, 11.85310f)
                close()
                moveTo(10.9907f, 4.32798f)
                curveTo(10.08130f, 4.3280f, 9.34410f, 5.06520f, 9.34410f, 5.97470f)
                verticalLineTo(8.31187f)
                curveTo(9.34410f, 9.53420f, 10.3350f, 10.52510f, 11.55730f, 10.52510f)
                horizontalLineTo(12.4426f)
                curveTo(13.6650f, 10.52510f, 14.65590f, 9.53420f, 14.65590f, 8.31190f)
                verticalLineTo(5.97466f)
                curveTo(14.65590f, 5.53790f, 14.48240f, 5.11910f, 14.17360f, 4.81030f)
                curveTo(13.86480f, 4.50150f, 13.4460f, 4.3280f, 13.00920f, 4.3280f)
                horizontalLineTo(10.9907f)
                close()
            }
        }.build()
        return _Profile!!
    }

private var _Profile: ImageVector? = null

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun ProfilePreview() {
    Icon(Profile, contentDescription = null)
}