package com.makeevrserg.koleso.feature.koleso.participants.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent

@Composable
fun ParticipantsContent(
    participantsComponent: ParticipantsComponent
) {
    val participantsModel by participantsComponent.model.collectAsState()
    Column {
        participantsModel.data.forEach { entry ->
            val arc = entry.arcModel
            val participant = entry.participantModel
            Row {
                Box(Modifier.size(24.dp).background(Color(arc.argbColor)))
                Text("${participant.desc} -> ${participant.point}")
            }
        }
    }
}
