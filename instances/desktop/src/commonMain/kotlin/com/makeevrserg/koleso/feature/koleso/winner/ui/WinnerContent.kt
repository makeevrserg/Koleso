package com.makeevrserg.koleso.feature.koleso.winner.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.winner.presentation.WinnerComponent

@Composable
fun WinnerContent(winnerComponent: WinnerComponent) {
    val model by winnerComponent.model.collectAsState()
    Crossfade(model) {
        when (val model = model) {
            WinnerComponent.Model.Pending -> Box(Modifier)
            is WinnerComponent.Model.Winner -> {
                Row {
                    Box(Modifier.size(24.dp).background(Color(model.participantWithArc.arcModel.argbColor)))
                    Text("${model.participantWithArc.arcModel}")
                }
            }
        }
    }
}