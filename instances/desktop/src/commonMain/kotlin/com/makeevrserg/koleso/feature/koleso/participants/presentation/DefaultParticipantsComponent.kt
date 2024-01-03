package com.makeevrserg.koleso.feature.koleso.participants.presentation

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantModel
import com.makeevrserg.koleso.feature.koleso.participants.domain.usecase.CreateParticipantWithArcUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultParticipantsComponent(
    componentContext: ComponentContext
) : ParticipantsComponent, ComponentContext by componentContext {
    private val createParticipantWithArcUseCase = CreateParticipantWithArcUseCaseImpl()
    override val model = MutableStateFlow(ParticipantsComponent.Model())

    private fun createParticipants() = listOf(
        ParticipantModel("_Pro_Kotleta_", 6),
        ParticipantModel("AJ3ToMAT", 12),
        ParticipantModel("apchiska", 8),
        ParticipantModel("RomaRoman", 3),
        ParticipantModel("Tombovskiy", 2),
    )

    override fun fillData(participants: List<ParticipantModel>) {
        model.value = ParticipantsComponent.Model(
            data = createParticipantWithArcUseCase.invoke(participants)
        )
    }

    init {
        fillData(createParticipants())
    }
}
