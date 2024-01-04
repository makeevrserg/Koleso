package com.makeevrserg.koleso.service.db.api

import app.cash.sqldelight.coroutines.asFlow
import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
import com.makeevrserg.koleso.service.db.api.util.map
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface ParticipantsApi {
    val participantsFlow: Flow<List<ParticipantModel>>

    suspend fun getParticipants(): List<ParticipantModel>

    suspend fun addParticipant(name: String, point: Long)

    suspend fun removeParticipant(participantModel: ParticipantModel)

    suspend fun update(participantModel: ParticipantModel)

    suspend fun getParticipantById(id: Long): ParticipantModel?
}

class ParticipantsApiImpl(private val querriesDeferred: Deferred<PlayerQueries>) : ParticipantsApi {
    private fun Participant.toDto() = ParticipantModel(
        id = id,
        point = point,
        desc = name,
    )
    override val participantsFlow: Flow<List<ParticipantModel>> = flow {
        querriesDeferred.map {
            it.selectAll().asFlow()
                .map { it.executeAsList() }
                .map { it.map { it.toDto() } }
        }.await().collect { emit(it) }
    }

    override suspend fun getParticipants(): List<ParticipantModel> {
        return querriesDeferred.await().selectAll().executeAsList()
            .map { it.toDto() }
    }

    override suspend fun addParticipant(name: String, point: Long) {
        querriesDeferred.await().upsert(null, point, name)
    }

    override suspend fun removeParticipant(participantModel: ParticipantModel) {
        querriesDeferred.await().delete(participantModel.id)
    }

    override suspend fun update(participantModel: ParticipantModel) {
        querriesDeferred.await().update(participantModel.point, participantModel.desc, participantModel.id)
    }

    override suspend fun getParticipantById(id: Long): ParticipantModel? {
        return querriesDeferred.await().selectById(id).executeAsOneOrNull()?.toDto()
    }
}
