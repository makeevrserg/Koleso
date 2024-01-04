package com.makeevrserg.koleso.feature.koleso.editparticipant.presentation

import kotlinx.coroutines.flow.StateFlow

interface EditParticipantComponent {
    val model: StateFlow<Model>

    fun changeName(name: String)

    fun changePoints(points: String)

    fun finishEdit()

    data class Model(
        val name: String = "",
        val pointsTextField: String = "1"
    ) {
        val isPointsValid = pointsTextField.toIntOrNull() != null
    }
}
