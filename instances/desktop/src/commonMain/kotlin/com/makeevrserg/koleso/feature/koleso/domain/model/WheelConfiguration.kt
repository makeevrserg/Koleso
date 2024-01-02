package com.makeevrserg.koleso.feature.koleso.domain.model

sealed interface WheelConfiguration {
    data object Pending : WheelConfiguration
    data class Wheeling(val degree: Float, val power: Float) : WheelConfiguration
    data class Wheeled(val degree: Float) : WheelConfiguration
}
