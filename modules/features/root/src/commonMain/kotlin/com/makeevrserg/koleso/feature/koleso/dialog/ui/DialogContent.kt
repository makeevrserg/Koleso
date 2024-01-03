package com.makeevrserg.koleso.feature.koleso.dialog.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.makeevrserg.koleso.feature.koleso.dialog.presentation.DialogComponent
import com.makeevrserg.koleso.feature.koleso.editparticipant.ui.EditParticipantContent

@Composable
fun DialogContent(dialogComponent: DialogComponent) {
    val child by dialogComponent.slot.subscribeAsState()
    when (val instance = child.child?.instance) {
        is DialogComponent.Child.EditParticipant -> {
            Dialog(onDismissRequest = { }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    EditParticipantContent(
                        editParticipantComponent = instance.editParticipantComponent,
                        onClose = dialogComponent::dismiss
                    )
                }
            }
        }

        null -> Unit
    }
}
