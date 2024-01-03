package com.makeevrserg.koleso.feature.koleso.wheel.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.min
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import kotlin.math.min
import androidx.compose.material3.MaterialTheme as Material3Theme

@Composable
fun Circle(
    data: List<ParticipantWithArc>,
    modifier: Modifier,
    backgroundColor: Color = Material3Theme.colorScheme.onTertiary,
    outlineWidth: Dp = 8.dp,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val minSize = min(drawContext.size.width, drawContext.size.height)
        drawArc(
            startAngle = 0f,
            sweepAngle = 360f,
            brush = Brush.radialGradient(
                colors = listOf(
                    backgroundColor,
                    backgroundColor.copy(0.8f),
                )
            ),
            useCenter = true,
            size = Size(minSize, minSize),
        )
        data.forEach { entry ->
            drawArc(
                startAngle = entry.arcModel.startAngle - 90,
                sweepAngle = entry.arcModel.sweepAngle,
                color = Color(entry.arcModel.argbColor).copy(1f),
                useCenter = true,
                size = Size(minSize - outlineWidth.value, minSize - outlineWidth.value),
                topLeft = Offset(outlineWidth.div(2f).value, outlineWidth.div(2f).value)
            )
            drawArc(
                startAngle = entry.arcModel.startAngle - 90,
                sweepAngle = entry.arcModel.sweepAngle,
                color = backgroundColor,
                useCenter = true,
                size = Size(minSize - outlineWidth.value, minSize - outlineWidth.value),
                topLeft = Offset(outlineWidth.div(2f).value, outlineWidth.div(2f).value),
                style = Stroke(
                    width = 2.dp.value,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                ),
            )
        }
    }
}
