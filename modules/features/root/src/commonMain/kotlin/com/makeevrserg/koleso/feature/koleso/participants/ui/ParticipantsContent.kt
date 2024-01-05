package com.makeevrserg.koleso.feature.koleso.participants.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DialogComponent
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.participants.ui.component.ParticipantWidget
import androidx.compose.material3.MaterialTheme as Material3Theme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ParticipantsContent(
    participantsComponent: ParticipantsComponent,
    dialogComponent: DialogComponent
) {
    val participantsModel by participantsComponent.model.collectAsState()
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Participants:",
            color = Material3Theme.colorScheme.onPrimaryContainer,
            style = Material3Theme.typography.titleLarge
        )
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.animateContentSize()
        ) {
            participantsModel.data.forEach { entry ->
                ParticipantWidget(
                    entry = entry,
                    onEditClicked = {
                        dialogComponent.openEditParticipant(entry.participantModel.id)
                    },
                    onDeleteClicked = {
                        participantsComponent.removeParticipant(entry.participantModel)
                    }
                )
            }
        }
    }
}
