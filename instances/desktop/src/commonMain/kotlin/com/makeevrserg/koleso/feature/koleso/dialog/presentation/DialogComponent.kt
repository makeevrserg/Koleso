package com.makeevrserg.koleso.feature.koleso.dialog.presentation

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.makeevrserg.koleso.feature.koleso.editparticipant.presentation.EditParticipantComponent

interface DialogComponent {
    val slot: Value<ChildSlot<*, Child>>

    fun openEditParticipant(id: String? = null)
    fun dismiss()

    sealed interface Child {
        class EditParticipant(val editParticipantComponent: EditParticipantComponent) : Child
    }
}
