package com.makeevrserg.koleso.service.db.api

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.db.SqlDriver
import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SQLiteParticipantsApi(driver: SqlDriver) : ParticipantsApi {
    private val playerQueries = PlayerQueries(driver)

    private fun Participant.toDto() = ParticipantModel(
        id = id,
        point = point,
        desc = name,
    )

    override val participantsFlow: Flow<List<ParticipantModel>> = flow {
        playerQueries
            .selectAll()
            .asFlow()
            .map { it.awaitAsList() }
            .map { it.map { it.toDto() } }
            .collect { emit(it) }
    }

    override suspend fun getParticipants(): List<ParticipantModel> {
        return playerQueries.selectAll().awaitAsList().map { it.toDto() }
    }

    override suspend fun addParticipant(name: String, point: Long) {
        playerQueries.upsert(null, point, name)
    }

    override suspend fun removeParticipant(participantModel: ParticipantModel) {
        playerQueries.delete(participantModel.id)
    }

    override suspend fun update(participantModel: ParticipantModel) {
        playerQueries.update(participantModel.point, participantModel.desc, participantModel.id)
    }

    override suspend fun getParticipantById(id: Long): ParticipantModel? {
        return playerQueries.selectById(id).awaitAsOneOrNull()?.toDto()
    }
}
