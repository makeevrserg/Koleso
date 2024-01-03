package com.makeevrserg.koleso.feature.koleso.winner.presentation

import com.makeevrserg.koleso.feature.koleso.participants.domain.model.ParticipantWithArc
import kotlinx.coroutines.flow.StateFlow

interface WinnerComponent {
    val model: StateFlow<Model>

    fun onWheelRotated(degree: Float, data: List<ParticipantWithArc>)
    fun reset()

    sealed interface Model {
        data object Pending : Model
        data class Winner(val participantWithArc: ParticipantWithArc) : Model
    }
}
