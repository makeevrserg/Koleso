package com.makeevrserg.koleso.feature.koleso.wheel.presentation

import com.makeevrserg.koleso.feature.koleso.wheel.domain.model.WheelConfiguration
import kotlinx.coroutines.flow.StateFlow

interface WheelComponent {
    val configuration: StateFlow<WheelConfiguration>

    fun startWheel()

    fun stopWheel()

    fun reset()
}
