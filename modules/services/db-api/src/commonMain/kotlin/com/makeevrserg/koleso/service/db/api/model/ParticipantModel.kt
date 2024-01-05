package com.makeevrserg.koleso.service.db.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ParticipantModel(
    val id: Long,
    val desc: String,
    val point: Long
)
