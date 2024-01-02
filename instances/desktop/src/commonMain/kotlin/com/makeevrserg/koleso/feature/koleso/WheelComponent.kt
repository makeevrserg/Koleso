package com.makeevrserg.koleso.feature.koleso

import kotlinx.coroutines.flow.StateFlow

interface WheelComponent {
    val model: StateFlow<Model>

    fun startWheel()

    fun stopWheel()

    fun reset()

    sealed interface Model {
        data object Pending : Model
        data class Wheeling(val degree: Float, val power: Float) : Model
        data class Wheeled(val degree: Float) : Model
    }
}