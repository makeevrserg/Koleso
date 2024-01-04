package com.makeevrserg.koleso.feature.koleso.participants.presentation

import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
import kotlinx.coroutines.flow.StateFlow

interface ParticipantsComponent {
    val model: StateFlow<Model>

    fun removeParticipant(participantModel: ParticipantModel)

    class Model(val data: List<ParticipantWithArc> = emptyList())
}
