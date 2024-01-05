package com.makeevrserg.koleso.service.db.api

import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
import com.makeevrserg.koleso.service.db.api.storage.JsonModelStorage
import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import ru.astrainteractive.klibs.kstorage.withDefault

class SettingsParticipantsApi(private val settings: Settings) : ParticipantsApi {
    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
    private val participantsStorage = JsonModelStorage<List<ParticipantModel>>(
        settings = settings,
        key = "participants",
        json = json
    ).withDefault(emptyList())

    override val participantsFlow = participantsStorage.stateFlow

    private fun getNextId(): Long {
        val currentMaxId = participantsFlow.value.maxByOrNull { it.id }?.id ?: 0L
        return currentMaxId + 1L
    }

    override suspend fun getParticipants(): List<ParticipantModel> {
        return participantsStorage.value
    }

    override suspend fun addParticipant(name: String, point: Long) {
        val id = getNextId()
        participantsStorage.update { currentParticipants ->
            currentParticipants + ParticipantModel(
                id = id,
                desc = name,
                point = point
            )
        }
    }

    override suspend fun removeParticipant(participantModel: ParticipantModel) {
        participantsStorage.update { currentParticipants ->
            currentParticipants - participantModel
        }
    }

    override suspend fun update(participantModel: ParticipantModel) {
        participantsStorage.update block@{ currentParticipants ->
            val i = currentParticipants.indexOfFirst { it.id == participantModel.id }
            if (i == -1) return@block currentParticipants
            val mutableParticipants = currentParticipants.toMutableList()
            mutableParticipants[i] = participantModel
            mutableParticipants
        }
    }

    override suspend fun getParticipantById(id: Long): ParticipantModel? {
        return participantsStorage.value.find { it.id == id }
    }
}
