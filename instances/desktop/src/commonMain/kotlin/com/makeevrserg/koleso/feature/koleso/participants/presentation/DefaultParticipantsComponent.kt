package com.makeevrserg.koleso.feature.koleso.participants.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.koleso.feature.koleso.participants.data.ParticipantsApi
import com.makeevrserg.koleso.feature.koleso.participants.data.model.ParticipantModel
import com.makeevrserg.koleso.feature.koleso.participants.domain.usecase.CreateParticipantWithArcUseCaseImpl
import com.makeevrserg.koleso.service.core.CoroutineFeature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DefaultParticipantsComponent(
    componentContext: ComponentContext
) : ParticipantsComponent, ComponentContext by componentContext {
    private val coroutineFeature = instanceKeeper.getOrCreate { CoroutineFeature.Default() }
    private val createParticipantWithArcUseCase = CreateParticipantWithArcUseCaseImpl()
    override val model = MutableStateFlow(ParticipantsComponent.Model())

    private fun fillData(participants: List<ParticipantModel>) {
        model.value = ParticipantsComponent.Model(
            data = createParticipantWithArcUseCase.invoke(participants)
        )
    }

    override fun removeParticipant(participantModel: ParticipantModel) {
        coroutineFeature.launch { ParticipantsApi.instance.removeParticipant(participantModel) }
    }

    init {
        ParticipantsApi.instance.participantsFlow
            .onEach { fillData(it) }
            .launchIn(coroutineFeature)
    }
}
