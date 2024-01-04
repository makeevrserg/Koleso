package com.makeevrserg.koleso.service.db.api

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.db.SqlDriver
import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
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

class ParticipantsApiImpl(private val driverDeferred: Deferred<SqlDriver>) : ParticipantsApi {
    private suspend fun getPlayerQueries(): PlayerQueries {
        val driver = driverDeferred.await()
        return PlayerQueries(driver)
    }

    private fun Participant.toDto() = ParticipantModel(
        id = id,
        point = point,
        desc = name,
    )

    override val participantsFlow: Flow<List<ParticipantModel>> = flow {
        getPlayerQueries()
            .selectAll()
            .asFlow()
            .map { it.awaitAsList() }
            .map { it.map { it.toDto() } }
            .collect { emit(it) }
    }

    override suspend fun getParticipants(): List<ParticipantModel> {
        return getPlayerQueries().selectAll().awaitAsList().map { it.toDto() }
    }

    override suspend fun addParticipant(name: String, point: Long) {
        getPlayerQueries().upsert(null, point, name)
    }

    override suspend fun removeParticipant(participantModel: ParticipantModel) {
        getPlayerQueries().delete(participantModel.id)
    }

    override suspend fun update(participantModel: ParticipantModel) {
        getPlayerQueries().update(participantModel.point, participantModel.desc, participantModel.id)
    }

    override suspend fun getParticipantById(id: Long): ParticipantModel? {
        return getPlayerQueries().selectById(id).awaitAsOneOrNull()?.toDto()
    }
}
