package com.makeevrserg.koleso.feature.koleso.participants.domain.model

import com.makeevrserg.koleso.service.db.api.model.ParticipantModel

data class ParticipantWithArc(
    val participantModel: ParticipantModel,
    val arcModel: ArcModel
)
