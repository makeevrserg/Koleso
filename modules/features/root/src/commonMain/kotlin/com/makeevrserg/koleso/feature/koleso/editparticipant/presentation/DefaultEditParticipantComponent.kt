package com.makeevrserg.koleso.feature.koleso.editparticipant.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.makeevrserg.koleso.feature.koleso.participants.data.ParticipantsApi
import com.makeevrserg.koleso.feature.koleso.participants.data.model.ParticipantModel
import com.makeevrserg.koleso.service.core.CoroutineFeature
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DefaultEditParticipantComponent(
    componentContext: ComponentContext,
    participantId: String = ""
) : EditParticipantComponent, ComponentContext by componentContext {
    private val coroutineFeature = instanceKeeper.getOrCreate { CoroutineFeature.Default() }

    override val model = MutableStateFlow(EditParticipantComponent.Model())

    private fun EditParticipantComponent.Model.toParticipantModel(): ParticipantModel {
        return ParticipantModel(
            desc = this.name,
            point = this.pointsTextField.toIntOrNull() ?: 0
        )
    }

    private fun ParticipantModel.toModel(): EditParticipantComponent.Model {
        return EditParticipantComponent.Model(
            name = this.desc,
            pointsTextField = this.point.toString()
        )
    }

    override fun delete() {
        coroutineFeature.launch {
            ParticipantsApi.instance.removeParticipant(model.value.toParticipantModel())
        }
    }

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
        model.update { it.copy(pointsTextField = points) }
    }

    override fun finishEdit() {
        coroutineFeature.launch {
            if (model.value.name.isEmpty()) return@launch
            if ((model.value.pointsTextField.toIntOrNull() ?: 0) == 0) return@launch
            ParticipantsApi.instance.addParticipant(model.value.toParticipantModel())
        }
    }

    init {
        coroutineFeature.launch {
            ParticipantsApi.instance.getParticipant(participantId)?.let { model.value = it.toModel() }
        }
    }
}
