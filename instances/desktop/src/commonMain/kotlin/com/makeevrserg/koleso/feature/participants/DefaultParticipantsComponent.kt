package com.makeevrserg.koleso.feature.participants

import com.arkivanov.decompose.ComponentContext
import com.makeevrserg.koleso.feature.participants.domain.model.ParticipantModel
import com.makeevrserg.koleso.feature.participants.domain.model.ParticipantWithArc
import com.makeevrserg.koleso.feature.participants.domain.usecase.CreateParticipantWithArcUseCaseImpl
import kotlin.random.Random
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultParticipantsComponent(
    componentContext: ComponentContext
) : ParticipantsComponent, ComponentContext by componentContext {
    private val createParticipantWithArcUseCase = CreateParticipantWithArcUseCaseImpl()
    override val model = MutableStateFlow(ParticipantsComponent.Model())
    private fun createParticipants() = List(Random.nextInt(3, 5)) {
        ParticipantModel("Participant $it", Random.nextInt(0, 14))
    }

    private fun createWiredModel(participants: List<ParticipantModel>): List<ParticipantWithArc> {
        return createParticipantWithArcUseCase.invoke(participants)
    }

    override fun fillData(participants: List<ParticipantModel>) {
        model.value = ParticipantsComponent.Model(
            data = createWiredModel(participants)
        )
    }

    init {
        fillData(createParticipants())
    }
}