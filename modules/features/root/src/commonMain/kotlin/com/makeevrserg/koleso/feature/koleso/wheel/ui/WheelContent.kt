package com.makeevrserg.koleso.feature.koleso.wheel.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.participants.presentation.ParticipantsComponent
import com.makeevrserg.koleso.feature.koleso.participants.ui.component.AddParticipantFloatingActionButton
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent
import com.makeevrserg.koleso.feature.koleso.wheel.ui.component.CircleWithArrow

@Composable
fun WheelContent(wheelComponent: WheelComponent, participantsComponent: ParticipantsComponent) {
    val wheelConfiguration by wheelComponent.configuration.collectAsState()
    val participantsModel by participantsComponent.model.collectAsState()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.animateContentSize()
    ) {
        CircleWithArrow(
            wheelConfiguration = wheelConfiguration,
            participantsModel = participantsModel
        )
    }
}

@Composable
fun EmptyWheelContent(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Participants is empty!\nAdd one",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        AddParticipantFloatingActionButton(isEnabled = true, onClick = onClick)
    }
}

@Composable
fun NotEnoughForWheelContent(onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Add one more participant!",
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        AddParticipantFloatingActionButton(isEnabled = true, onClick = onClick)
    }
}
