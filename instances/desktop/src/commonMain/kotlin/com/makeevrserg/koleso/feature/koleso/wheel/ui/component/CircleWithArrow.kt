package com.makeevrserg.koleso.feature.koleso.wheel.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.wheel.domain.model.WheelConfiguration

@Composable
fun CircleWithArrow(
    wheelConfiguration: WheelConfiguration,
    participantsModel: ParticipantsComponent.Model
) {
    val degree by animateFloatAsState(wheelConfiguration.degree)
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.Red
        )
        Circle(data = participantsModel.data, modifier = Modifier.size(128.dp).rotate(degree))
    }
}
