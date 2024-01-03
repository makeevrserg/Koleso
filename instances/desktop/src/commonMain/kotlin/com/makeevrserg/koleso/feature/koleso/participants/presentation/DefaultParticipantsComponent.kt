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
        ParticipantModel("Part 0", 6),
        ParticipantModel("Part 1", 0),
        ParticipantModel("Part 2", 5),
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