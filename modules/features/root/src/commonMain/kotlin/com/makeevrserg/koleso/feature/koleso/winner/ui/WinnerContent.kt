package com.makeevrserg.koleso.feature.koleso.winner.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.winner.presentation.WinnerComponent
import androidx.compose.material3.MaterialTheme as Material3Theme

@Composable
private fun WinnerRotatingContent(model: WinnerComponent.Model.Rotating) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.height(4.dp)
                .width(24.dp)
                .background(Color(model.participantWithArc.arcModel.argbColor))
        )
        Text(
            text = "✨${model.participantWithArc.participantModel.desc}✨",
            textAlign = TextAlign.Center,
            color = Material3Theme.colorScheme.onPrimaryContainer
        )
        Box(
            modifier = Modifier.height(4.dp)
                .width(24.dp)
                .background(Color(model.participantWithArc.arcModel.argbColor))
        )
    }
}

@Composable
private fun WinnerCompletedContent(model: WinnerComponent.Model.Winner) {
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.height(4.dp)
                    .width(24.dp)
                    .background(Color(model.participantWithArc.arcModel.argbColor))
            )
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "✨Winner is ${model.participantWithArc.participantModel.desc}!✨",
                    textAlign = TextAlign.Center,
                    color = Material3Theme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "\uD83D\uDE80Total points: ${model.participantWithArc.participantModel.point}!\uD83D\uDE80",
                    textAlign = TextAlign.Center,
                    color = Material3Theme.colorScheme.onPrimaryContainer
                )
            }
            Box(
                modifier = Modifier.height(4.dp)
                    .width(24.dp)
                    .background(Color(model.participantWithArc.arcModel.argbColor))
            )
        }
    }
}

@Composable
fun WinnerContent(winnerComponent: WinnerComponent) {
    val model by winnerComponent.model.collectAsState()
    Crossfade(targetState = model, modifier = Modifier.animateContentSize()) {
        when (val model = model) {
            WinnerComponent.Model.Pending -> Box(Modifier)
            is WinnerComponent.Model.Rotating -> {
                WinnerRotatingContent(model = model)
            }

            is WinnerComponent.Model.Winner -> {
                WinnerCompletedContent(model = model)
            }
        }
    }
}
