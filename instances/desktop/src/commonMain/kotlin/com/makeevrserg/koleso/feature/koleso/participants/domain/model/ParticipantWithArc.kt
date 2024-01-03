package com.makeevrserg.koleso.feature.koleso.participants.domain.model

import com.makeevrserg.koleso.feature.koleso.participants.data.model.ParticipantModel

data class ParticipantWithArc(
    val participantModel: ParticipantModel,
    val arcModel: ArcModel
)
