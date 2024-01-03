package com.makeevrserg.koleso.feature.koleso.wheel.domain.model

sealed interface WheelConfiguration {
    val degree: Float
    data class Pending(override val degree: Float) : WheelConfiguration
    data class Wheeling(override val degree: Float, val power: Float) : WheelConfiguration
    data class Wheeled(override val degree: Float) : WheelConfiguration
}
