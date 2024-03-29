package com.makeevrserg.koleso.feature.koleso.editparticipant.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.koleso.service.core.CoroutineFeature
import com.makeevrserg.koleso.service.db.api.ParticipantsApi
import com.makeevrserg.koleso.service.db.api.model.ParticipantModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DefaultEditParticipantComponent(
    componentContext: ComponentContext,
    private val participantId: Long? = null,
    private val participantsApi: ParticipantsApi,
    private val mainScope: CoroutineScope
) : EditParticipantComponent, ComponentContext by componentContext {
    private val coroutineFeature = instanceKeeper.getOrCreate { CoroutineFeature.Default() }

    override val model = MutableStateFlow(EditParticipantComponent.Model())

    override fun changeName(name: String) {
        model.update {
            it.copy(
                name = name.trim()
                    .replace("\n", "")
                    .replace("\r", "")
                    .replace("\t", "")
            )
        }
    }

    override fun changePoints(points: String) {
        model.update {
            it.copy(
                pointsTextField = points.trim()
                    .replace("\n", "")
                    .replace("\r", "")
                    .replace("\t", "")
            )
        }
    }

    override fun finishEdit() {
        mainScope.launch {
            if (model.value.name.isEmpty()) return@launch
            if ((model.value.pointsLong) <= 0) return@launch
            if (participantId == null) {
                participantsApi.addParticipant(
                    name = model.value.name,
                    point = model.value.pointsLong
                )
            } else {
                participantsApi.update(
                    ParticipantModel(
                        participantId,
                        model.value.name,
                        model.value.pointsLong
                    )
                )
            }
        }
    }

    init {
        coroutineFeature.launch {
            participantId ?: return@launch
            participantsApi.getParticipantById(participantId)?.let {
                model.value = EditParticipantComponent.Model(
                    name = it.desc,
                    pointsTextField = it.point.toString()
                )
            }
        }
    }
}
