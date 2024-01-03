package com.makeevrserg.koleso.feature.participants

import  com.makeevrserg.koleso.feature.participants.domain.model.ParticipantModel
import  com.makeevrserg.koleso.feature.participants.domain.model.ArcModel
import com.makeevrserg.koleso.feature.participants.domain.model.ParticipantWithArc
import kotlinx.coroutines.flow.StateFlow

interface ParticipantsComponent {
    val model: StateFlow<Model>

    fun fillData(participants: List<ParticipantModel>)

    class Model(val data: List<ParticipantWithArc> = emptyList())
}