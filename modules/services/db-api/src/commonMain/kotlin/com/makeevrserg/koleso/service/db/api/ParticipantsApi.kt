package com.makeevrserg.koleso.service.db.api

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import app.cash.sqldelight.coroutines.asFlow
import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.dispatchers.DefaultKotlinDispatchers

interface ParticipantsApi {
    suspend fun getParticipantsFlow(): Flow<List<ParticipantModel>>

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

    override suspend fun getParticipantsFlow(): Flow<List<ParticipantModel>> = querriesDeferred.await().selectAll()
        .asFlow()
        .map { it.awaitAsList() }
        .map { it.map { it.toDto() } }

    override suspend fun getParticipants(): List<ParticipantModel> = withContext(DefaultKotlinDispatchers.IO) {
        querriesDeferred.await().selectAll().awaitAsList()
            .map { it.toDto() }
    }

    override suspend fun addParticipant(name: String, point: Long) = withContext(DefaultKotlinDispatchers.IO) {
        querriesDeferred.await().upsert(null, point, name)
    }

    override suspend fun removeParticipant(participantModel: ParticipantModel) =
        withContext(DefaultKotlinDispatchers.IO) {
            querriesDeferred.await().delete(participantModel.id)
        }

    override suspend fun update(participantModel: ParticipantModel) = withContext(DefaultKotlinDispatchers.IO) {
        querriesDeferred.await().update(participantModel.point, participantModel.desc, participantModel.id)
    }

    override suspend fun getParticipantById(id: Long): ParticipantModel? = withContext(DefaultKotlinDispatchers.IO) {
        querriesDeferred.await().selectById(id).awaitAsOneOrNull()?.toDto()
    }
}
