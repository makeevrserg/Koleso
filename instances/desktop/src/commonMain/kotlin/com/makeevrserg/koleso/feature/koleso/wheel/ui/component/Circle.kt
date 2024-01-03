package com.makeevrserg.koleso.feature.koleso.wheel.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import androidx.compose.material3.MaterialTheme as Material3Theme

@Composable
fun Circle(
    data: List<ParticipantWithArc>,
    modifier: Modifier,
    backgroundColor: Color = Material3Theme.colorScheme.tertiaryContainer,
    outlineWidth: Dp = 8.dp,
    size: Dp
) {
    Canvas(modifier = modifier.size(size)) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    backgroundColor,
                    backgroundColor.copy(0.8f),
                )
            ),
            radius = (size).value / 2,
        )
        data.forEach { entry ->
            drawArc(
                startAngle = entry.arcModel.startAngle - 90,
                sweepAngle = entry.arcModel.sweepAngle,
                color = Color(entry.arcModel.argbColor).copy(1f),
                useCenter = true,
                topLeft = Offset(outlineWidth.value / 2, outlineWidth.value / 2),
                size = Size((size - outlineWidth).value, (size - outlineWidth).value),
            )
            drawArc(
                startAngle = entry.arcModel.startAngle - 90,
                sweepAngle = entry.arcModel.sweepAngle,
                color = backgroundColor,
                useCenter = true,
                topLeft = Offset(outlineWidth.value / 2, outlineWidth.value / 2),
                size = Size((size - outlineWidth).value, (size - outlineWidth).value),
                style = Stroke(
                    width = 2.dp.value,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                ),
            )
        }
    }
}
