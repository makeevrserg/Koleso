package com.makeevrserg.koleso.feature.koleso.dialog.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.makeevrserg.koleso.feature.koleso.editparticipant.presentation.DefaultEditParticipantComponent
import com.makeevrserg.koleso.service.db.api.ParticipantsApi

class DefaultDialogComponent(
    componentContext: ComponentContext,
    private val participantsApi: ParticipantsApi
) : DialogComponent,
    ComponentContext by componentContext {

    private val slotNavigation = SlotNavigation<Configuration>()

    override val slot: Value<ChildSlot<*, DialogComponent.Child>> = childSlot(
        source = slotNavigation,
        handleBackButton = true,
    ) { config, childComponentContext ->
        when (config) {
            is Configuration.EditParticipant -> DialogComponent.Child.EditParticipant(
                editParticipantComponent = DefaultEditParticipantComponent(
                    componentContext = childComponentContext,
                    participantId = config.id,
                    participantsApi = participantsApi
                )
            )
        }
    }

    override fun dismiss() {
        slotNavigation.dismiss()
    }

    override fun openEditParticipant(id: Long?) {
        slotNavigation.activate(
            Configuration.EditParticipant(id)
        )
    }

    private sealed interface Configuration : Parcelable {
        @Parcelize
        class EditParticipant(val id: Long? = null) : Configuration
    }
}
