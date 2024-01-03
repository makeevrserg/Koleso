package com.makeevrserg.koleso.feature.koleso.wheel.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc

@Composable
fun Circle(
    data: List<ParticipantWithArc>,
    modifier: Modifier = Modifier
) {
    val size = 128.dp.value
    Canvas(modifier = modifier) {
        data.forEach { entry ->
            drawArc(
                startAngle = entry.arcModel.startAngle - 90,
                sweepAngle = entry.arcModel.sweepAngle,
                color = Color(entry.arcModel.argbColor),
                useCenter = true,
                size = Size(size, size)
            )
        }
    }
}
