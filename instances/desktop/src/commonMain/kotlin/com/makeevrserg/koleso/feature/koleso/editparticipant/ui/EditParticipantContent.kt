package com.makeevrserg.koleso.feature.koleso.editparticipant.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.makeevrserg.koleso.feature.koleso.editparticipant.presentation.EditParticipantComponent
import androidx.compose.material3.MaterialTheme as Material3Theme

@Composable
fun EditParticipantContent(
    editParticipantComponent: EditParticipantComponent,
    onClose: () -> Unit
) {
    val model by editParticipantComponent.model.collectAsState()
    Column(
        modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Participant name:",
            style = Material3Theme.typography.titleMedium,
            color = Material3Theme.colorScheme.onSecondaryContainer
        )
        TextField(
            value = model.name,
            onValueChange = editParticipantComponent::changeName,
            textStyle = Material3Theme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Participant points:",
            style = Material3Theme.typography.titleMedium,
            color = Material3Theme.colorScheme.onSecondaryContainer
        )
        TextField(
            value = model.pointsTextField,
            onValueChange = editParticipantComponent::changePoints,
            textStyle = Material3Theme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            TextButton(onClick = {
                editParticipantComponent.finishEdit()
                onClose.invoke()
            }) {
                Text(
                    text = "Complete",
                    style = Material3Theme.typography.titleMedium,
                    color = Material3Theme.colorScheme.tertiary
                )
            }
            TextButton(onClick = onClose) {
                Text(
                    text = "Cancel",
                    style = Material3Theme.typography.titleMedium,
                    color = Material3Theme.colorScheme.error
                )
            }
        }
    }
}
