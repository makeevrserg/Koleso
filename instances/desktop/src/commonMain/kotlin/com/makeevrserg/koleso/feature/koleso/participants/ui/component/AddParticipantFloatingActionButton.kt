package com.makeevrserg.koleso.feature.koleso.participants.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.makeevrserg.koleso.feature.koleso.wheel.domain.model.WheelConfiguration
import com.makeevrserg.koleso.feature.koleso.wheel.presentation.WheelComponent
import kotlinx.coroutines.flow.distinctUntilChanged
import androidx.compose.material3.MaterialTheme as Material3Theme

@Composable
fun AddParticipantFloatingActionButton(
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    FloatingActionButton(
        containerColor = when {
            isEnabled -> Material3Theme.colorScheme.secondaryContainer
            else -> Material3Theme.colorScheme.secondaryContainer
        },
        onClick = {
            if (isEnabled) onClick.invoke()
        },
        content = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = when {
                    isEnabled -> Material3Theme.colorScheme.onSecondaryContainer
                    else -> Material3Theme.colorScheme.onSecondaryContainer.copy(0.2f)
                }
            )
        }
    )
}

@Composable
fun AddParticipantFloatingActionButton(
    wheelComponent: WheelComponent,
    onClick: () -> Unit
) {
    val model by wheelComponent.configuration
        .distinctUntilChanged { old, new -> old::class == new::class }
        .collectAsState(WheelConfiguration.Pending(0f))
    AddParticipantFloatingActionButton(isEnabled = model !is WheelConfiguration.Wheeling, onClick = onClick)
}
