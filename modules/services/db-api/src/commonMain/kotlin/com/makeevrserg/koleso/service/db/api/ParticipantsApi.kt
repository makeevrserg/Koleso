package com.makeevrserg.koleso.service.db.api

import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
import kotlinx.coroutines.flow.Flow

interface ParticipantsApi {
    val participantsFlow: Flow<List<ParticipantModel>>

    suspend fun getParticipants(): List<ParticipantModel>

    suspend fun addParticipant(name: String, point: Long)

    suspend fun removeParticipant(participantModel: ParticipantModel)

    suspend fun update(participantModel: ParticipantModel)

    suspend fun getParticipantById(id: Long): ParticipantModel?
}
