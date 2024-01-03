package com.makeevrserg.koleso.feature.koleso.participants.data

import com.makeevrserg.koleso.feature.koleso.participants.data.model.ParticipantModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ParticipantsApi {
    val participantsFlow: Flow<List<ParticipantModel>>
    suspend fun getParticipants(): List<ParticipantModel>
    suspend fun addParticipant(participantModel: ParticipantModel)
    suspend fun removeParticipant(participantModel: ParticipantModel)
    suspend fun getParticipant(id: String): ParticipantModel?

    companion object {
        val instance: ParticipantsApi = ParticipantsApiImpl()
    }
}

private class ParticipantsApiImpl : ParticipantsApi {

    private val participants = MutableStateFlow(
        listOf(
            ParticipantModel("_Pro_Kotleta_", 6),
            ParticipantModel("AJ3ToMAT", 12),
            ParticipantModel("apchiska", 8),
            ParticipantModel("RomaRoman", 3),
            ParticipantModel("Tombovskiy", 2),
        )
    )
    override val participantsFlow: Flow<List<ParticipantModel>> = participants

    override suspend fun getParticipants(): List<ParticipantModel> {
        return participants.value
    }

    override suspend fun addParticipant(participantModel: ParticipantModel) {
        participants.value = participants.value.filter { it.desc != participantModel.desc } + participantModel
    }

    override suspend fun removeParticipant(participantModel: ParticipantModel) {
        participants.value -= participantModel
    }

    override suspend fun getParticipant(id: String): ParticipantModel? {
        return participants.value.firstOrNull { it.desc == id }
    }
}
