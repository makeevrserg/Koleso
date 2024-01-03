package com.makeevrserg.koleso.feature.koleso.participants.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DialogComponent
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import androidx.compose.material3.MaterialTheme as Material3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParticipantContent(entry: ParticipantWithArc, onDeleteClicked: () -> Unit, onEditClicked: () -> Unit) {
    val arc = entry.arcModel
    val participant = entry.participantModel
    Card(
        modifier = Modifier.height(IntrinsicSize.Min),
        colors = CardDefaults.cardColors(
            containerColor = Material3Theme.colorScheme.surfaceVariant,
        ),
        onClick = onEditClicked
    ) {
        Row(modifier = Modifier.padding(8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.fillMaxHeight().width(8.dp).clip(CircleShape).background(Color(arc.argbColor)))
            Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.width(IntrinsicSize.Min)) {
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = null,
                        tint = Material3Theme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = participant.desc,
                        color = Material3Theme.colorScheme.onPrimaryContainer,
                        style = Material3Theme.typography.bodyMedium
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Material3Theme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "${participant.point}",
                            color = Material3Theme.colorScheme.onPrimaryContainer,
                            style = Material3Theme.typography.bodyMedium
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Material3Theme.colorScheme.error,
                        modifier = Modifier.clip(CircleShape).clickable {
                            onDeleteClicked.invoke()
                        }
                    )
                }
            }
        }
    }
}

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
                ParticipantContent(
                    entry = entry,
                    onEditClicked = {
                        dialogComponent.openEditParticipant(entry.participantModel.desc)
                    },
                    onDeleteClicked = {
                        participantsComponent.removeParticipant(entry.participantModel)
                    }
                )
            }
        }
    }
}
